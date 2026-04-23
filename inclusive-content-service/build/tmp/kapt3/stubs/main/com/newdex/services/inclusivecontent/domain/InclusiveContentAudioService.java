package com.newdex.services.inclusivecontent.domain;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ0\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\rH\u0016J8\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\rH\u0012J \u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0014H\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioService;", "", "audioRepository", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioRepository;", "audioGeneratorService", "Lcom/newdex/services/inclusivecontent/domain/AudioGeneratorService;", "fileServiceClient", "Lcom/newdex/services/inclusivecontent/domain/FileServiceClient;", "(Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioRepository;Lcom/newdex/services/inclusivecontent/domain/AudioGeneratorService;Lcom/newdex/services/inclusivecontent/domain/FileServiceClient;)V", "log", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "buildAudioCacheKey", "", "text", "images", "", "Lorg/springframework/web/multipart/MultipartFile;", "imageUrls", "generateAndSaveAudio", "", "lessonId", "", "generateAndSaveAudioFromLesson", "getAudioByLessonId", "getCachedAudio", "cacheKey", "saveAudio", "audioBytes", "inclusive-content-service"})
public class InclusiveContentAudioService {
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.InclusiveContentAudioRepository audioRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.AudioGeneratorService audioGeneratorService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.FileServiceClient fileServiceClient = null;
    private final org.slf4j.Logger log = null;
    
    public InclusiveContentAudioService(@org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.InclusiveContentAudioRepository audioRepository, @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.AudioGeneratorService audioGeneratorService, @org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.FileServiceClient fileServiceClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] generateAndSaveAudio(long lessonId, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] generateAndSaveAudioFromLesson(long lessonId, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.springframework.web.multipart.MultipartFile> images, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> imageUrls) {
        return null;
    }
    
    private byte[] saveAudio(long lessonId, java.lang.String cacheKey, byte[] audioBytes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public byte[] getAudioByLessonId(long lessonId) {
        return null;
    }
    
    private byte[] getCachedAudio(long lessonId, java.lang.String cacheKey) {
        return null;
    }
    
    private java.lang.String buildAudioCacheKey(java.lang.String text, java.util.List<? extends org.springframework.web.multipart.MultipartFile> images, java.util.List<java.lang.String> imageUrls) {
        return null;
    }
}