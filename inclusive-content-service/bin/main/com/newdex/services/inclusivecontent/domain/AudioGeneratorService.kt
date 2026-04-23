package com.newdex.services.inclusivecontent.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.time.Instant

@Service
class AudioGeneratorService(
    @Value("\${inclusive-content.audio-generator.url:http://bridge-converter:8000}")
    private val audioGeneratorUrl: String,
    private val imageDescriptionRepository: InclusiveContentImageDescriptionRepository,
) {
    private val log = LoggerFactory.getLogger(AudioGeneratorService::class.java)
    private val restTemplate = RestTemplate()

    fun generateFromText(text: String): ByteArray {
        val formData = LinkedMultiValueMap<String, Any>()
        formData.add("text", text)

        return sendMultipartRequest(
            url = "$audioGeneratorUrl/audio/from-text",
            formData = formData,
        )
    }

    fun generateFromLesson(
        text: String,
        images: List<MultipartFile>?,
        imageUrls: List<String>?,
    ): ByteArray {
        val formData = LinkedMultiValueMap<String, Any>()
        formData.add("text", text)
        images.orEmpty().forEach { image ->
            if (image.isEmpty) return@forEach
            formData.add(
                "image",
                MultipartByteArrayResource(
                    bytes = image.bytes,
                    filename = image.originalFilename ?: "lesson-image",
                    contentType = image.contentType,
                ),
            )
        }
        imageUrls.orEmpty()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { formData.add("imageUrl", it) }

        return sendMultipartRequest(
            url = "$audioGeneratorUrl/audio/from-lesson",
            formData = formData,
        )
    }

    fun describeImage(image: MultipartFile?, imageUrl: String?): String {
        val cacheKey = buildImageDescriptionCacheKey(image = image, imageUrl = imageUrl)
        if (!cacheKey.isNullOrBlank()) {
            val cached = imageDescriptionRepository.findByCacheKey(cacheKey).orElse(null)
            if (cached != null && cached.description.isNotBlank()) {
                log.info("Image description cache HIT key={}", cacheKey)
                return cached.description
            }
            log.info("Image description cache MISS key={}", cacheKey)
        } else {
            log.info("Image description cache SKIP key=none")
        }

        val formData = LinkedMultiValueMap<String, Any>()
        if (image != null && !image.isEmpty) {
            formData.add(
                "image",
                MultipartByteArrayResource(
                    bytes = image.bytes,
                    filename = image.originalFilename ?: "lesson-image",
                    contentType = image.contentType,
                ),
            )
        }
        val cleanedUrl = imageUrl?.trim().orEmpty()
        if (cleanedUrl.isNotBlank()) {
            formData.add("imageUrl", cleanedUrl)
        }
        if (formData.isEmpty) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Either image or imageUrl must be provided")
        }

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }
        val requestEntity = HttpEntity(formData, headers)
        val response = restTemplate.exchange(
            "$audioGeneratorUrl/image/describe",
            HttpMethod.POST,
            requestEntity,
            BridgeImageDescriptionResponse::class.java,
        )
        val body = response.body
        if (!response.statusCode.is2xxSuccessful || body?.description.isNullOrBlank()) {
            throw ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Image description failed with status ${response.statusCode.value()}",
            )
        }
        val description = body!!.description.trim()
        if (!cacheKey.isNullOrBlank() && description.isNotBlank()) {
            val now = Instant.now()
            val cached = imageDescriptionRepository.findByCacheKey(cacheKey).orElse(null)
            if (cached == null) {
                imageDescriptionRepository.save(
                    InclusiveContentImageDescriptionEntity(
                        cacheKey = cacheKey,
                        description = description,
                        createdAt = now,
                        updatedAt = now,
                    )
                )
                log.info("Image description cache SAVE key={} action=insert", cacheKey)
            } else {
                cached.description = description
                cached.updatedAt = now
                imageDescriptionRepository.save(cached)
                log.info("Image description cache SAVE key={} action=update", cacheKey)
            }
        }
        return description
    }

    private fun buildImageDescriptionCacheKey(image: MultipartFile?, imageUrl: String?): String? {
        if (image != null && !image.isEmpty) {
            val digest = MessageDigest.getInstance("SHA-256").digest(image.bytes)
            return "sha256:${digest.joinToString("") { "%02x".format(it) }}"
        }
        val normalizedUrl = imageUrl?.trim()?.lowercase().orEmpty()
        if (normalizedUrl.isBlank()) {
            return null
        }
        return "url:$normalizedUrl"
    }

    private fun sendMultipartRequest(
        url: String,
        formData: LinkedMultiValueMap<String, Any>,
    ): ByteArray {
        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val requestEntity = HttpEntity(formData, headers)
        val response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            ByteArray::class.java,
        )

        if (!response.statusCode.is2xxSuccessful || response.body == null) {
            throw ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Audio generator failed with status ${response.statusCode.value()}",
            )
        }

        return response.body!!
    }
}

private class MultipartByteArrayResource(
    private val bytes: ByteArray,
    private val filename: String,
    private val contentType: String?,
) : ByteArrayResource(bytes) {
    override fun getFilename(): String = filename

    override fun getDescription(): String = contentType ?: super.getDescription()
}

data class BridgeImageDescriptionResponse(
    val description: String,
)
