package com.newdex.services.inclusivecontent.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface InclusiveContentAudioRepository : JpaRepository<InclusiveContentAudioEntity, Long> {
    fun findByLessonId(lessonId: Long): Optional<InclusiveContentAudioEntity>
}
