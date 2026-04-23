package com.newdex.services.inclusivecontent.config

import com.newdex.services.common.exception.ErrorResponse
import com.newdex.services.common.exception.ErrorType
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
@Order(2)
class InclusiveContentExceptionHandler {
    @ExceptionHandler
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val statusCode = e.statusCode
        val errorType = when (statusCode.value()) {
            HttpStatus.NOT_FOUND.value() -> ErrorType.NOT_FOUND
            HttpStatus.BAD_REQUEST.value() -> ErrorType.INVALID_DATA
            HttpStatus.CONFLICT.value() -> ErrorType.ALREADY_EXIST
            HttpStatus.UNAUTHORIZED.value() -> ErrorType.UNAUTHORIZED
            HttpStatus.FORBIDDEN.value() -> ErrorType.FORBIDDEN
            else -> ErrorType.INVALID_OPERATION
        }
        return ResponseEntity
            .status(statusCode.value())
            .body(
                ErrorResponse(
                    status = errorType,
                    description = e.reason ?: "Request failed",
                )
            )
    }

    @ExceptionHandler
    fun handleMaxUploadSizeExceededException(
        e: MaxUploadSizeExceededException,
    ): ResponseEntity<ErrorResponse> = ResponseEntity
        .status(HttpStatus.PAYLOAD_TOO_LARGE)
        .body(
            ErrorResponse(
                status = ErrorType.INVALID_DATA,
                description = "Image is too large. Maximum upload size is 10MB.",
            )
        )
}
