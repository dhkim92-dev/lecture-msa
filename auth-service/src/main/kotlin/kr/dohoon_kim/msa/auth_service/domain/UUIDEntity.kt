package kr.dohoon_kim.msa.auth_service.domain

import jakarta.persistence.*
import jakarta.persistence.Id
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.UUID

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class UUIDEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID? = null
): BaseTimeEntity() {

    val identifier: UUID
        get() = id!!

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UUIDEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}