package com.newdex.services.inclusivecontent.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface InclusiveContentImageDescriptionRepository :
    JpaRepository<InclusiveContentImageDescriptionEntity, Long> {
    fun findByCacheKey(cacheKey: String): Optional<InclusiveContentImageDescriptionEntity>
}
