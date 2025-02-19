package kr.dohoon_kim.msa.auth_service.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MemberAuthenticationInfoRepository: JpaRepository<MemberAuthenticationInfo, UUID> {

    @Query(
        """
            SELECT mai
            FROM MemberAuthenticationInfo mai
            WHERE mai.id = :#{#id.value}
        """
    )
    fun findByIdOrNull(id: ID<MemberAuthenticationInfo, UUID>): MemberAuthenticationInfo?

    fun findByEmail(email: String): MemberAuthenticationInfo?
}