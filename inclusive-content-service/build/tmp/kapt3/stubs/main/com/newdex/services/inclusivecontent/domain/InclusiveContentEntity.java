package com.newdex.services.inclusivecontent.domain;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "inclusive_content")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0017\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u001e\u0010\u0006\u001a\u00020\u00078\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentEntity;", "", "id", "", "text", "", "createdAt", "Ljava/time/Instant;", "(JLjava/lang/String;Ljava/time/Instant;)V", "getCreatedAt", "()Ljava/time/Instant;", "setCreatedAt", "(Ljava/time/Instant;)V", "getId", "()J", "setId", "(J)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "inclusive-content-service"})
public class InclusiveContentEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String text;
    @jakarta.persistence.Column(name = "created_at", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.time.Instant createdAt;
    
    public InclusiveContentEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt) {
        super();
    }
    
    public long getId() {
        return 0L;
    }
    
    public void setId(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getText() {
        return null;
    }
    
    public void setText(@org.jetbrains.annotations.NotNull()
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