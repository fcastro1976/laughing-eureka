package com.newdex.services.inclusivecontent.domain

import org.springframework.http.HttpStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.multipart.MultipartFile
import java.security.MessageDigest
import java.time.Instant

@Service
class InclusiveContentAudioService(
    private val audioRepository: InclusiveContentAudioRepository,
    private val audioGeneratorService: AudioGeneratorService,
    private val fileServiceClient: FileServiceClient,
) {
    private val log = LoggerFactory.getLogger(InclusiveContentAudioService::class.java)

    fun generateAndSaveAudio(lessonId: Long, text: String): ByteArray {
        val cacheKey = buildAudioCacheKey(text = text, images = null, imageUrls = null)
        getCachedAudio(lessonId = lessonId, cacheKey = cacheKey)?.let { return it }
        val audioBytes = audioGeneratorService.generateFromText(text)
        return saveAudio(lessonId, cacheKey, audioBytes)
    }

    fun generateAndSaveAudioFromLesson(
        lessonId: Long,
        text: String,
        images: List<MultipartFile>?,
        imageUrls: List<String>?,
    ): ByteArray {
        val cacheKey = buildAudioCacheKey(text = text, images = images, imageUrls = imageUrls)
        getCachedAudio(lessonId = lessonId, cacheKey = cacheKey)?.let { return it }
        val audioBytes = audioGeneratorService.generateFromLesson(text, images, imageUrls)
        return saveAudio(lessonId, cacheKey, audioBytes)
    }

    private fun saveAudio(lessonId: Long, cacheKey: String, audioBytes: ByteArray): ByteArray {
        val fileId = fileServiceClient.uploadAudio(
            bytes = audioBytes,
            filename = "lesson-$lessonId.mp3",
        )

        val existing = audioRepository.findByLessonId(lessonId).orElse(null)
        val entity = if (existing == null) {
            InclusiveContentAudioEntity(
                lessonId = lessonId,
                fileId = fileId,
                cacheKey = cacheKey,
                createdAt = Instant.now(),
            )
        } else {
            existing.fileId = fileId
            existing.cacheKey = cacheKey
            existing.createdAt = Instant.now()
            existing
        }

        audioRepository.save(entity)
        return audioBytes
    }

    fun getAudioByLessonId(lessonId: Long): ByteArray {
        val entity = audioRepository.findByLessonId(lessonId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audio not found for lesson")
        }
        val fileId = entity.fileId
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Audio file not found for lesson")
        return fileServiceClient.downloadAudio(fileId)
    }

    private fun getCachedAudio(lessonId: Long, cacheKey: String): ByteArray? {
        val existing = audioRepository.findByLessonId(lessonId).orElse(null) ?: return null
        if (existing.cacheKey != cacheKey) {
            log.info("Lesson audio cache MISS lessonId={} reason=cache-key-mismatch", lessonId)
            return null
        }
        val fileId = existing.fileId
        if (fileId == null) {
            log.info("Lesson audio cache MISS lessonId={} reason=file-id-null", lessonId)
            return null
        }
        return try {
            log.info("Lesson audio cache HIT lessonId={}", lessonId)
            fileServiceClient.downloadAudio(fileId)
        } catch (_: Exception) {
            log.warn("Lesson audio cache MISS lessonId={} reason=download-failed", lessonId)
            null
        }
    }

    private fun buildAudioCacheKey(
        text: String,
        images: List<MultipartFile>?,
        imageUrls: List<String>?,
    ): String {
        val normalizedText = text.trim().replace(Regex("\\s+"), " ")
        val imageParts = images.orEmpty()
            .filter { !it.isEmpty }
            .map { image ->
                val digest = MessageDigest.getInstance("SHA-256").digest(image.bytes)
                "img:${digest.joinToString("") { "%02x".format(it) }}"
            }
            .sorted()
        val urlParts = imageUrls.orEmpty()
            .map { it.trim().lowercase() }
            .filter { it.isNotBlank() }
            .sorted()
            .map { "url:$it" }
        val payload = buildString {
            append("text:")
            append(normalizedText)
            append("|media:")
            append((imageParts + urlParts).joinToString(","))
        }
        val digest = MessageDigest.getInstance("SHA-256").digest(payload.toByteArray())
        return "sha256:${digest.joinToString("") { "%02x".format(it) }}"
    }
}
