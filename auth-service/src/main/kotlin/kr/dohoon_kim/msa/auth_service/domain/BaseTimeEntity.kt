package kr.dohoon_kim.msa.auth_service.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class BaseTimeEntity {

    @CreatedDate
    private var createdAt: Instant = Instant.now()

    @LastModifiedDate
    private var updatedAt: Instant? = null

    @PrePersist
    fun onPrePersist() {
        createdAt = Instant.now()
    }

    @PreUpdate
    fun onPreUpdate() {
        updatedAt = Instant.now()
    }

    fun getCreatedAt(): Instant {
        return createdAt
    }

    fun getUpdatedAt(): Instant? {
        return updatedAt
    }
}