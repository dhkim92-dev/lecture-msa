package kr.dohoon_kim.msa.auth_service.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity<T>(
    @Id
    private val id: T? = null
): BaseTimeEntity() {

    val identifier: T
        get() = id!!

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity<*>

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}