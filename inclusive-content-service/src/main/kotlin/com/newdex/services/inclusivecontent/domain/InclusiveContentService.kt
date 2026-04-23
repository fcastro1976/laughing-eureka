package com.newdex.services.inclusivecontent.domain

import com.newdex.services.inclusivecontent.api.http.dto.CreateInclusiveContentRequest
import com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@Service
class InclusiveContentService(
    private val repository: InclusiveContentRepository,
) {
    fun create(request: CreateInclusiveContentRequest): InclusiveContentResponse {
        val entity = InclusiveContentEntity(
            text = request.text.trim(),
            createdAt = Instant.now(),
        )
        val saved = repository.save(entity)
        return saved.toResponse()
    }

    fun get(id: Long): InclusiveContentResponse {
        val entity = repository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Inclusive content not found")
        }
        return entity.toResponse()
    }

    private fun InclusiveContentEntity.toResponse(): InclusiveContentResponse =
        InclusiveContentResponse(
            id = id,
            text = text,
            createdAt = createdAt,
        )
}
