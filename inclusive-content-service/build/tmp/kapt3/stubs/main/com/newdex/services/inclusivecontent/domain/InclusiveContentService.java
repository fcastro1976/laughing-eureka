package com.newdex.services.inclusivecontent.domain;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\f\u0010\f\u001a\u00020\u0006*\u00020\rH\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentService;", "", "repository", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentRepository;", "(Lcom/newdex/services/inclusivecontent/domain/InclusiveContentRepository;)V", "create", "Lcom/newdex/services/inclusivecontent/api/http/dto/InclusiveContentResponse;", "request", "Lcom/newdex/services/inclusivecontent/api/http/dto/CreateInclusiveContentRequest;", "get", "id", "", "toResponse", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentEntity;", "inclusive-content-service"})
public class InclusiveContentService {
    @org.jetbrains.annotations.NotNull()
    private final com.newdex.services.inclusivecontent.domain.InclusiveContentRepository repository = null;
    
    public InclusiveContentService(@org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.domain.InclusiveContentRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse create(@org.jetbrains.annotations.NotNull()
    com.newdex.services.inclusivecontent.api.http.dto.CreateInclusiveContentRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse get(long id) {
        return null;
    }
    
    private com.newdex.services.inclusivecontent.api.http.dto.InclusiveContentResponse toResponse(com.newdex.services.inclusivecontent.domain.InclusiveContentEntity $this$toResponse) {
        return null;
    }
}