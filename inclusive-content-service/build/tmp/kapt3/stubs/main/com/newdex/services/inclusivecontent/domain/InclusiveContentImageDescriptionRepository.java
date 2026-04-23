package com.newdex.services.inclusivecontent.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/newdex/services/inclusivecontent/domain/InclusiveContentImageDescriptionRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/newdex/services/inclusivecontent/domain/InclusiveContentImageDescriptionEntity;", "", "findByCacheKey", "Ljava/util/Optional;", "cacheKey", "", "inclusive-content-service"})
public abstract interface InclusiveContentImageDescriptionRepository extends org.springframework.data.jpa.repository.JpaRepository<com.newdex.services.inclusivecontent.domain.InclusiveContentImageDescriptionEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Optional<com.newdex.services.inclusivecontent.domain.InclusiveContentImageDescriptionEntity> findByCacheKey(@org.jetbrains.annotations.NotNull()
    java.lang.String cacheKey);
}