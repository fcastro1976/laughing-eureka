package com.newdex.services.inclusivecontent.api.http.dto

import java.time.Instant

data class InclusiveContentResponse(
    val id: Long,
    val text: String,
    val createdAt: Instant,
)
