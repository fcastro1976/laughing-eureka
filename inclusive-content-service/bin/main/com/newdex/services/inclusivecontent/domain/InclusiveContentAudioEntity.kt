package com.newdex.services.inclusivecontent.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "inclusive_content_audio")
class InclusiveContentAudioEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "lesson_id", nullable = false, unique = true)
    var lessonId: Long,

    @Column(name = "file_id")
    var fileId: UUID? = null,

    @Column(name = "cache_key", length = 512)
    var cacheKey: String? = null,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant,
)
