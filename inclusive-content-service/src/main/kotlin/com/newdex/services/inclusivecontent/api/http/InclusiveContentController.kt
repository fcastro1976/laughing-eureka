package com.newdex.services.inclusivecontent.api.http

import com.newdex.services.inclusivecontent.api.http.dto.CreateInclusiveContentRequest
import com.newdex.services.inclusivecontent.api.http.dto.ImageDescriptionResponse
import com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse
import com.newdex.services.inclusivecontent.api.http.dto.TextToAudioRequest
import com.newdex.services.inclusivecontent.domain.AudioGeneratorService
import com.newdex.services.inclusivecontent.domain.InclusiveContentAudioService
import com.newdex.services.inclusivecontent.domain.InclusiveContentService
import jakarta.validation.Valid
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/inclusive-content")
class InclusiveContentController(
    private val service: InclusiveContentService,
    private val audioGeneratorService: AudioGeneratorService,
    private val audioService: InclusiveContentAudioService,
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CreateInclusiveContentRequest): InclusiveContentResponse =
        service.create(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): InclusiveContentResponse =
        service.get(id)

    @PostMapping("/audio/from-text")
    fun generateAudioFromText(@Valid @RequestBody request: TextToAudioRequest): ResponseEntity<ByteArrayResource> {
        val text = request.text.trim()
        validateWordLimit(text)
        val audioBytes = audioGeneratorService.generateFromText(text)

        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType("audio/mpeg")
            contentDisposition = ContentDisposition.attachment().filename("lesson.mp3").build()
            contentLength = audioBytes.size.toLong()
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(ByteArrayResource(audioBytes))
    }

    @PostMapping("/lessons/{lessonId}/audio/from-text")
    fun generateLessonAudioFromText(
        @PathVariable lessonId: Long,
        @Valid @RequestBody request: TextToAudioRequest,
    ): ResponseEntity<ByteArrayResource> {
        val text = request.text.trim()
        validateWordLimit(text)
        val audioBytes = audioService.generateAndSaveAudio(
            lessonId = lessonId,
            text = text,
        )

        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType("audio/mpeg")
            contentDisposition = ContentDisposition.attachment()
                .filename("lesson-$lessonId.mp3")
                .build()
            contentLength = audioBytes.size.toLong()
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(ByteArrayResource(audioBytes))
    }

    @PostMapping("/lessons/{lessonId}/audio/from-lesson", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun generateLessonAudioFromLesson(
        @PathVariable lessonId: Long,
        @RequestParam("text") text: String,
        @RequestParam("image", required = false) images: List<MultipartFile>?,
        @RequestParam("imageUrl", required = false) imageUrls: List<String>?,
    ): ResponseEntity<ByteArrayResource> {
        val lessonText = text.trim()
        if (lessonText.isBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Text cannot be empty")
        }
        validateWordLimit(lessonText)

        val audioBytes = audioService.generateAndSaveAudioFromLesson(
            lessonId = lessonId,
            text = lessonText,
            images = images,
            imageUrls = imageUrls,
        )

        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType("audio/mpeg")
            contentDisposition = ContentDisposition.attachment()
                .filename("lesson-$lessonId.mp3")
                .build()
            contentLength = audioBytes.size.toLong()
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(ByteArrayResource(audioBytes))
    }

    @PostMapping("/images/describe", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun describeImage(
        @RequestParam("image", required = false) image: MultipartFile?,
        @RequestParam("imageUrl", required = false) imageUrl: String?,
    ): ImageDescriptionResponse {
        val description = audioGeneratorService.describeImage(image = image, imageUrl = imageUrl)
        return ImageDescriptionResponse(description = description)
    }

    @GetMapping("/lessons/{lessonId}/audio")
    fun getLessonAudio(@PathVariable lessonId: Long): ResponseEntity<ByteArrayResource> {
        val audioBytes = audioService.getAudioByLessonId(lessonId)
        val headers = HttpHeaders().apply {
            contentType = MediaType.parseMediaType("audio/mpeg")
            contentDisposition = ContentDisposition.attachment()
                .filename("lesson-$lessonId.mp3")
                .build()
            contentLength = audioBytes.size.toLong()
        }
        return ResponseEntity.ok()
            .headers(headers)
            .body(ByteArrayResource(audioBytes))
    }

    private fun validateWordLimit(text: String) {
        val words = text.trim().split(Regex("\\s+")).filter { it.isNotBlank() }
        if (words.size > 500) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Максимальное количество слов для озвучки: 500"
            )
        }
    }
}
