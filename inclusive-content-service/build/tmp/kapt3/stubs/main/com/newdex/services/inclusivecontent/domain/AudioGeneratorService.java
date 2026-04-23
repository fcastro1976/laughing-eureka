package com.newdex.services.inclusivecontent.domain;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0012J\u001c\u0010\u0010\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u001bH\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/AudioGeneratorService;", "", "audioGeneratorUrl", "", "imageDescriptionRepository", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentImageDescriptionRepository;", "(Ljava/lang/String;Lcom/newdex/services/inclusivecontent/domain/InclusiveContentImageDescriptionRepository;)V", "log", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "restTemplate", "Lorg/springframework/web/client/RestTemplate;", "buildImageDescriptionCacheKey", "image", "Lorg/springframework/web/multipart/MultipartFile;", "imageUrl", "describeImage", "generateFromLesson", "", "text", "images", "", "imageUrls", "generateFromText", "sendMultipartRequest", "url", "formData", "Lorg/springframework/util/LinkedMultiValueMap;", "inclusive-content-service"})
public class AudioGeneratorService {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String audioGeneratorUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.InclusiveContentImageDescriptionRepository imageDescriptionRepository = null;
    private final org.slf4j.Logger log = null;
    @org.jetbrains.annotations.NotNull()
    private final org.springframework.web.client.RestTemplate restTemplate = null;
    
    public AudioGeneratorService(@org.springframework.beans.factory.annotation.Value(value = "${inclusive-content.audio-generator.url:http://bridge-converter:8000}")
    @org.jetbrains.annotations.NotNull()
    java.lang.String audioGeneratorUrl, @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.InclusiveContentImageDescriptionRepository imageDescriptionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] generateFromText(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] generateFromLesson(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.springframework.web.multipart.MultipartFile> images, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> imageUrls) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String describeImage(@org.jetbrains.annotations.Nullable()
    org.springframework.web.multipart.MultipartFile image, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl) {
        return null;
    }
    
    private java.lang.String buildImageDescriptionCacheKey(org.springframework.web.multipart.MultipartFile image, java.lang.String imageUrl) {
        return null;
    }
    
    private byte[] sendMultipartRequest(java.lang.String url, org.springframework.util.LinkedMultiValueMap<java.lang.String, java.lang.Object> formData) {
        return null;
    }
}