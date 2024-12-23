package kr.dohoon_kim.msa.auth_service.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberAuthenticationInfoRepository: JpaRepository<MemberAuthenticationInfo, MemberId> {

    fun findByNickname(nickname: String): MemberAuthenticationInfo?
}