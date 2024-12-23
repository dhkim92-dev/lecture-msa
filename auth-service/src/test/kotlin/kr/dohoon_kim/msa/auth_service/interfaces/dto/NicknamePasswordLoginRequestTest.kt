package kr.dohoon_kim.msa.auth_service.interfaces.dto

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kr.dohoon_kim.msa.auth_service.helper.BaseAnnotationSpecUnitTest

class NicknamePasswordLoginRequestTest: BaseAnnotationSpecUnitTest() {

    @Test
    fun `닉네임 패스워드 로그인 요청 객체가 정상인 경우 에러가 발생하지 않는다`() {
        // Given
        val request = NicknamePasswordLoginRequest(nickname = "nickname", password = "password")
        // When
        val violations = validator.validate(request)
        // Then
        violations.size shouldBe 0
    }

    @Test
    fun `닉네임이 없는 경우 에러가 발생한다`() {
        // Given
        val request = NicknamePasswordLoginRequest(nickname = "", password = "password")
        // When
        val violations = validator.validate(request)
        // Then
        violations.size shouldBe 1
    }

    @Test
    fun `패스워드가 없는 경우 에러가 발생한다`() {
        // Given
        val request = NicknamePasswordLoginRequest(nickname = "nickname", password = "")
        // When
        val violations = validator.validate(request)
        // Then
        violations.size shouldBe 1
    }
}