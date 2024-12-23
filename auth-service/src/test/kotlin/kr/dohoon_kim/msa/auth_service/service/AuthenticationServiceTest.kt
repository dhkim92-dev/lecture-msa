package kr.dohoon_kim.msa.auth_service.service

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kr.dohoon_kim.lectures.msa.common.responses.errors.NotFoundException
import kr.dohoon_kim.lectures.msa.common.responses.errors.UnauthorizedException
import kr.dohoon_kim.msa.auth_service.domain.MemberAuthenticationInfo
import kr.dohoon_kim.msa.auth_service.domain.MemberAuthenticationInfoRepository
import kr.dohoon_kim.msa.auth_service.domain.MemberId
import kr.dohoon_kim.msa.auth_service.helper.JwtHelper
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

class AuthenticationServiceTest: AnnotationSpec() {

    private val memberAuthenticationInfoRepository = mockk<MemberAuthenticationInfoRepository>()
    private val jwtService = JWTService(JwtHelper.createJwtConfigs())
    private val passwordEncoder = mockk<PasswordEncoder>()
    private val authenticationService = AuthenticationService(memberAuthenticationInfoRepository, jwtService, passwordEncoder)

    private val memberId = MemberId(UUID.randomUUID())
    private val defaultMember = MemberAuthenticationInfo(
        id = memberId,
        nickname = "test",
        password = "test"
    )
    private lateinit var refreshToken: String

    @BeforeEach
    fun setUp() {
        refreshToken = jwtService.createRefreshToken(memberId)
    }

    @Test
    fun `사용자 닉네임과 비밀번호를 통한 인증`() {
        // given
        every { memberAuthenticationInfoRepository.findByNickname("test") } returns defaultMember
        every { passwordEncoder.matches("test", "test") } returns true
        // when
        val result = authenticationService.nicknamePasswordAuthentication("test", "test")

        // then
        result.accessToken shouldNotBe null
        result.refreshToken shouldNotBe null
        result.type shouldBe "Bearer"

        shouldNotThrowAny {
            jwtService.verifyAccessToken(result.accessToken)
            jwtService.verifyRefreshToken(result.refreshToken)
        }
    }

    @Test
    fun`회원이 존재하지 않는 경우 UnauthorizedException 발생`() {
        every { memberAuthenticationInfoRepository.findByNickname("test") } returns null

        shouldThrow<UnauthorizedException> {
            authenticationService.nicknamePasswordAuthentication("test", "test")
        }
    }

    @Test
    fun `회원이 존재하지만 비밀번호가 일치하지 않는 경우 UnauthorizedException 발생`() {
        every { memberAuthenticationInfoRepository.findByNickname("test") } returns defaultMember
        every { passwordEncoder.matches(any(), any()) } returns false

        shouldThrow<UnauthorizedException> {
            authenticationService.nicknamePasswordAuthentication("test", "wrong")
        }
    }

    @AfterEach
    fun cleanUp() {
        clearAllMocks()
    }
}