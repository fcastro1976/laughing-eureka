package com.newdex.services.inclusivecontent.domain;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "inclusive_content_audio")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0017\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bR \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\t\u001a\u00020\n8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001b\u00a8\u0006\u001e"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentAudioEntity;", "", "id", "", "lessonId", "fileId", "Ljava/util/UUID;", "cacheKey", "", "createdAt", "Ljava/time/Instant;", "(JJLjava/util/UUID;Ljava/lang/String;Ljava/time/Instant;)V", "getCacheKey", "()Ljava/lang/String;", "setCacheKey", "(Ljava/lang/String;)V", "getCreatedAt", "()Ljava/time/Instant;", "setCreatedAt", "(Ljava/time/Instant;)V", "getFileId", "()Ljava/util/UUID;", "setFileId", "(Ljava/util/UUID;)V", "getId", "()J", "setId", "(J)V", "getLessonId", "setLessonId", "inclusive-content-service"})
public class InclusiveContentAudioEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @jakarta.persistence.Column(name = "lesson_id", nullable = false, unique = true)
    private long lessonId;
    @jakarta.persistence.Column(name = "file_id")
    @org.jetbrains.annotations.Nullable()
    private java.util.UUID fileId;
    @jakarta.persistence.Column(name = "cache_key", length = 512)
    @org.jetbrains.annotations.Nullable()
    private java.lang.String cacheKey;
    @jakarta.persistence.Column(name = "created_at", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.Instant createdAt;
    
    public InclusiveContentAudioEntity(long id, long lessonId, @org.jetbrains.annotations.Nullable()
    java.util.UUID fileId, @org.jetbrains.annotations.Nullable()
    java.lang.String cacheKey, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt) {
        super();
    }
    
    public long getId() {
        return 0L;
    }
    
    public void setId(long p0) {
    }
    
    public long getLessonId() {
        return 0L;
    }
    
    public void setLessonId(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.util.UUID getFileId() {
        return null;
    }
    
    public void setFileId(@org.jetbrains.annotations.Nullable()
    java.util.UUID p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getCacheKey() {
        return null;
    }
    
    public void setCacheKey(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.Instant getCreatedAt() {
        return null;
    }
    
    public void setCreatedAt(@org.jetbrains.annotations.NotNull()
    java.time.Instant p0) {
    }
}