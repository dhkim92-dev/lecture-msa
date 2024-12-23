package kr.dohoon_kim.msa.auth_service.domain

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.util.*

class MemberAUthenticationInfoTest : AnnotationSpec() {

    @Test
    fun `생성자를 통한 객체 생성`() {
        val memberId = MemberId(UUID.randomUUID())

        val member = MemberAuthenticationInfo(
            id = memberId,
            nickname = "test",
            password = "test"
        )

        member.identifier shouldBe memberId
        member.nickname shouldBe "test"
        member.password shouldBe "test"
    }

    @Test
    fun `닉네임 변경 테스트`() {
        val memberId = MemberId(UUID.randomUUID())

        val member = MemberAuthenticationInfo(
            id = memberId,
            nickname = "test",
            password = "test"
        )

        member.changeNickname("new")

        member.nickname shouldBe "new"
    }

    @Test
    fun `비밀번호 변경 테스트`() {
        val memberId = MemberId(UUID.randomUUID())

        val member = MemberAuthenticationInfo(
            id = memberId,
            nickname = "test",
            password = "test"
        )

        member.changePassword("new")

        member.password shouldBe "new"
    }
}