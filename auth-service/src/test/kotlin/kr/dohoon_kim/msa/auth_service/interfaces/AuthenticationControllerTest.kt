package kr.dohoon_kim.msa.auth_service.interfaces

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kr.dohoon_kim.msa.auth_service.interfaces.dto.NicknamePasswordLoginRequest
import kr.dohoon_kim.msa.auth_service.interfaces.dto.RefreshTokenRequest
import kr.dohoon_kim.msa.auth_service.service.AuthenticationService
import kr.dohoon_kim.msa.auth_service.service.dto.AuthenticationSuccess
import kotlin.math.exp

class AuthenticationControllerTest: AnnotationSpec() {

    private val authenticationService = mockk<AuthenticationService>()

    private val authenticationController = AuthenticationController(authenticationService)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `닉네임 패스워드 로그인 요청`() {
        // Given 로그인 요청이 주어진다.
        val request = NicknamePasswordLoginRequest("nickname", "password")
        val expected = AuthenticationSuccess(
            type = "Bearer",
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )

        // When 로그인이 성공하면
        every { authenticationService.nicknamePasswordAuthentication(any(), any()) } returns expected

        shouldNotThrowAny {
            val response = authenticationController.login(request)
            // Then 인증 응답 객체가 반환된다.
            response.refreshToken shouldNotBe null
            response.accessToken shouldNotBe  null
            response.tokenType shouldBe "Bearer"
        }

    }

    @Test
    fun `토큰 재발급 요청`() {
        //Given
        val request = RefreshTokenRequest(type = "Bearer", refreshToken = "refreshToken")
        val expected = AuthenticationSuccess(
            type = "Bearer",
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )

        // When
        every { authenticationService.refreshAccessToken(any()) } returns expected

        //Then
        shouldNotThrowAny {
            val response = authenticationController.refresh(request)
            response.refreshToken shouldNotBe null
            response.accessToken shouldNotBe null
            response.tokenType shouldBe "Bearer"
        }
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}