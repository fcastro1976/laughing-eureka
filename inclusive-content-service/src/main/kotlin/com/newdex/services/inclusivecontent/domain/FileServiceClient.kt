package com.newdex.services.inclusivecontent.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.UUID

@Service
class FileServiceClient(
    @Value("\${inclusive-content.file-service.url:http://file-service:8080}")
    private val fileServiceUrl: String,
) {
    private val restTemplate = RestTemplate()

    fun uploadAudio(bytes: ByteArray, filename: String): UUID {
        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("file", ByteArrayResourceWithFilename(bytes, filename))
        }

        val request = HttpEntity(body, headers)
        val responseType = object : ParameterizedTypeReference<List<FileInfoResponse>>() {}

        val response = restTemplate.exchange(
            "$fileServiceUrl/api/v1/files?operationType=ARBITRARY_BLOCK",
            org.springframework.http.HttpMethod.POST,
            request,
            responseType,
        )

        val fileInfo = response.body?.firstOrNull()
            ?: throw IllegalStateException("File-service returned empty response")
        return fileInfo.id
    }

    fun downloadAudio(fileId: UUID): ByteArray {
        val response = restTemplate.getForEntity(
            "$fileServiceUrl/api/v1/files/$fileId?operationType=ARBITRARY_BLOCK",
            ByteArray::class.java,
        )
        return response.body ?: throw IllegalStateException("File-service returned empty body")
    }
}

data class FileInfoResponse(
    val id: UUID,
    val name: String,
)

private class ByteArrayResourceWithFilename(
    private val bytes: ByteArray,
    private val filename: String,
) : org.springframework.core.io.ByteArrayResource(bytes) {
    override fun getFilename(): String = filename
}
