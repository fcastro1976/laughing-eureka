package com.newdex.services.inclusivecontent.domain;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "inclusive_content_image_description")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0017\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nR\u001e\u0010\u0004\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0007\u001a\u00020\b8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\t\u001a\u00020\b8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012\u00a8\u0006\u001b"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentImageDescriptionEntity;", "", "id", "", "cacheKey", "", "description", "createdAt", "Ljava/time/Instant;", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;Ljava/time/Instant;Ljava/time/Instant;)V", "getCacheKey", "()Ljava/lang/String;", "setCacheKey", "(Ljava/lang/String;)V", "getCreatedAt", "()Ljava/time/Instant;", "setCreatedAt", "(Ljava/time/Instant;)V", "getDescription", "setDescription", "getId", "()J", "setId", "(J)V", "getUpdatedAt", "setUpdatedAt", "inclusive-content-service"})
public class InclusiveContentImageDescriptionEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @jakarta.persistence.Column(name = "cache_key", nullable = false, unique = true, length = 512)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String cacheKey;
    @jakarta.persistence.Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @org.jetbrains.annotations.NotNull()
    private java.lang.String description;
    @jakarta.persistence.Column(name = "created_at", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.Instant createdAt;
    @jakarta.persistence.Column(name = "updated_at", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.Instant updatedAt;
    
    public InclusiveContentImageDescriptionEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String cacheKey, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt, @org.jetbrains.annotations.NotNull()
    java.time.Instant updatedAt) {
        super();
    }
    
    public long getId() {
        return 0L;
    }
    
    public void setId(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getCacheKey() {
        return null;
    }
    
    public void setCacheKey(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getDescription() {
        return null;
    }
    
    public void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.Instant getCreatedAt() {
        return null;
    }
    
    public void setCreatedAt(@org.jetbrains.annotations.NotNull()
    java.time.Instant p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.Instant getUpdatedAt() {
        return null;
    }
    
    public void setUpdatedAt(@org.jetbrains.annotations.NotNull()
    java.time.Instant p0) {
    }
}