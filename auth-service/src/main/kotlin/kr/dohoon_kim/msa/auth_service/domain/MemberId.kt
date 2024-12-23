package kr.dohoon_kim.msa.auth_service.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class MemberId(
    val id: UUID
) {
    constructor(): this(UUID.randomUUID())
}