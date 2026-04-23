package com.newdex.services.inclusivecontent.api.http;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/api/v1/inclusive-content"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0017J \u0010\r\u001a\u00020\u000e2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0017J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0001\u0010\u000b\u001a\u00020\u0016H\u0017JF\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u001a\u001a\u00020\u00122\u0010\b\u0001\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001c2\u0010\b\u0001\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001cH\u0017J\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u000b\u001a\u00020\u0016H\u0017J\u0012\u0010\u001f\u001a\u00020\n2\b\b\u0001\u0010 \u001a\u00020\u0019H\u0017J\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0017J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u0012H\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/newdex/services/inclusivecontent/api/http/InclusiveContentController;", "", "service", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentService;", "audioGeneratorService", "Lcom/newdex/services/inclusivecontent/domain/AudioGeneratorService;", "audioService", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioService;", "(Lcom/newdex/services/inclusivecontent/domain/InclusiveContentService;Lcom/newdex/services/inclusivecontent/domain/AudioGeneratorService;Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioService;)V", "create", "Lcom/newdex/services/inclusivecontent/api/http/dto/InclusiveContentResponse;", "request", "Lcom/newdex/services/inclusivecontent/api/http/dto/CreateInclusiveContentRequest;", "describeImage", "Lcom/newdex/services/inclusivecontent/api/http/dto/ImageDescriptionResponse;", "image", "Lorg/springframework/web/multipart/MultipartFile;", "imageUrl", "", "generateAudioFromText", "Lorg/springframework/http/ResponseEntity;", "Lorg/springframework/core/io/ByteArrayResource;", "Lcom/newdex/services/inclusivecontent/api/http/dto/TextToAudioRequest;", "generateLessonAudioFromLesson", "lessonId", "", "text", "images", "", "imageUrls", "generateLessonAudioFromText", "get", "id", "getLessonAudio", "validateWordLimit", "", "inclusive-content-service"})
public class InclusiveContentController {
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.InclusiveContentService service = null;
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.AudioGeneratorService audioGeneratorService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.InclusiveContentAudioService audioService = null;
    
    public InclusiveContentController(@org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.InclusiveContentService service, @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.AudioGeneratorService audioGeneratorService, @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.InclusiveContentAudioService audioService) {
        super();
    }
    
    @org.springframework.web.bind.annotation.PostMapping()
    @org.jetbrains.annotations.NotNull()
    public com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse create(@jakarta.validation.Valid()
    @org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.api.http.dto.CreateInclusiveContentRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.GetMapping(value = {"/{id}"})
    @org.jetbrains.annotations.NotNull()
    public com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse get(@org.springframework.web.bind.annotation.PathVariable()
    long id) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/audio/from-text"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<org.springframework.core.io.ByteArrayResource> generateAudioFromText(@jakarta.validation.Valid()
    @org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.api.http.dto.TextToAudioRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/lessons/{lessonId}/audio/from-text"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<org.springframework.core.io.ByteArrayResource> generateLessonAudioFromText(@org.springframework.web.bind.annotation.PathVariable()
    long lessonId, @jakarta.validation.Valid()
    @org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.api.http.dto.TextToAudioRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/lessons/{lessonId}/audio/from-lesson"}, consumes = {"multipart/form-data"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<org.springframework.core.io.ByteArrayResource> generateLessonAudioFromLesson(@org.springframework.web.bind.annotation.PathVariable()
    long lessonId, @org.springframework.web.bind.annotation.RequestParam(value = "text")
    @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.springframework.web.bind.annotation.RequestParam(value = "image", required = false)
    @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.springframework.web.multipart.MultipartFile> images, @org.springframework.web.bind.annotation.RequestParam(value = "imageUrl", required = false)
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> imageUrls) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/images/describe"}, consumes = {"multipart/form-data"})
    @org.jetbrains.annotations.NotNull()
    public com.newdex.services.inclusivecontent.api.http.dto.ImageDescriptionResponse describeImage(@org.springframework.web.bind.annotation.RequestParam(value = "image", required = false)
    @org.jetbrains.annotations.Nullable()
    org.springframework.web.multipart.MultipartFile image, @org.springframework.web.bind.annotation.RequestParam(value = "imageUrl", required = false)
    @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.GetMapping(value = {"/lessons/{lessonId}/audio"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<org.springframework.core.io.ByteArrayResource> getLessonAudio(@org.springframework.web.bind.annotation.PathVariable()
    long lessonId) {
        return null;
    }
    
    private void validateWordLimit(java.lang.String text) {
    }
}