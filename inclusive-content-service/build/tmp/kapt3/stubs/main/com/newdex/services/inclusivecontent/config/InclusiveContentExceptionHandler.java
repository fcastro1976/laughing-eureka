package com.newdex.services.inclusivecontent.config;

@org.springframework.web.bind.annotation.ControllerAdvice()
@org.springframework.core.annotation.Order(value = 2)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\tH\u0017\u00a8\u0006\n"}, d2 = {"Lcom/newdex/services/inclusivecontent/config/InclusiveContentExceptionHandler;", "", "()V", "handleMaxUploadSizeExceededException", "Lorg/springframework/http/ResponseEntity;", "Lcom/newdex/services/common/exception/ErrorResponse;", "e", "Lorg/springframework/web/multipart/MaxUploadSizeExceededException;", "handleResponseStatusException", "Lorg/springframework/web/server/ResponseStatusException;", "inclusive-content-service"})
public class InclusiveContentExceptionHandler {
    
    public InclusiveContentExceptionHandler() {
        super();
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.newdex.services.common.exception.ErrorResponse> handleResponseStatusException(@org.jetbrains.annotations.NotNull()
    org.springframework.web.server.ResponseStatusException e) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.newdex.services.common.exception.ErrorResponse> handleMaxUploadSizeExceededException(@org.jetbrains.annotations.NotNull()
    org.springframework.web.multipart.MaxUploadSizeExceededException e) {
        return null;
    }
}