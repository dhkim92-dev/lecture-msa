package kr.dohoon_kim.msa.auth_service.service

import com.auth0.jwt.exceptions.JWTVerificationException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kr.dohoon_kim.msa.auth_service.configs.JwtConfiguration
import kr.dohoon_kim.msa.auth_service.domain.MemberId
import org.junit.jupiter.api.Test
import java.util.*

class JWTServiceTest(): AnnotationSpec() {

    private val jwtConfiguration = JwtConfiguration(
        accessTokenSecret = "accessToken",
        refreshTokenSecret = "refreshToken",
        accessTokenExpiration = 600000,
        refreshTokenExpiration = 86400000,
        issuer = "issuer"
    )

    private lateinit var publishedAccessToken: String
    private lateinit var memberId: MemberId
    private lateinit var jwtService: JWTService

    @BeforeEach
    fun setUp() {
        jwtService = JWTService(jwtConfiguration)
        memberId = MemberId(UUID.randomUUID())
        publishedAccessToken = jwtService.createAccessToken(memberId)
    }


    @Test
    fun `createAccessToken 테스트`() {
        val memberId = MemberId(UUID.randomUUID())
        val token = jwtService.createAccessToken(memberId)
        val decoded = jwtService.verifyAccessToken(token)
        decoded.subject shouldBe memberId.id.toString()
    }

    @Test
    fun `createRefreshToken 테스트`() {
        val memberId = MemberId(UUID.randomUUID())
        val token = jwtService.createRefreshToken(memberId)
        val decoded = jwtService.verifyRefreshToken(token)
        decoded.subject shouldBe memberId.id.toString()
    }

    @Test
    fun `정상 엑세스 토큰 검증`() {
        val decoded = jwtService.verifyAccessToken(publishedAccessToken)
        decoded.subject shouldBe memberId.id.toString()
    }

    @Test
    fun `변조된 엑세스 토큰 검증 시 에러발생`() {
        val token = publishedAccessToken.substring(0, publishedAccessToken.length - 1)

        shouldThrow<JWTVerificationException> {
            jwtService.verifyAccessToken(token)
        }
    }

    @Test
    fun `발급자가 다른 엑세스 토큰 검증 시 에러발생`() {
        val fakeToken = JWTService(JwtConfiguration(
            accessTokenSecret = "accessToken",
            refreshTokenSecret = "refreshToken",
            accessTokenExpiration = 600000,
            refreshTokenExpiration = 86400000,
            issuer = "fake"
        )).createAccessToken(memberId)

        shouldThrow<JWTVerificationException> {
            jwtService.verifyAccessToken(fakeToken)
        }
    }
}