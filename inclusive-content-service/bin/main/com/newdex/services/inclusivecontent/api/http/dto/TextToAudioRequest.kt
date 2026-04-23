package com.newdex.services.inclusivecontent.api.http.dto

import jakarta.validation.constraints.NotBlank

data class TextToAudioRequest(
    @field:NotBlank
    val text: String,
)
