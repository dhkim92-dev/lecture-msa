package kr.dohoon_kim.msa.auth_service.service

import com.auth0.jwt.exceptions.JWTVerificationException
import kr.dohoon_kim.lectures.msa.common.responses.errors.NotFoundException
import kr.dohoon_kim.lectures.msa.common.responses.errors.UnauthorizedException
import kr.dohoon_kim.msa.auth_service.codes.ServiceErrorCode
import kr.dohoon_kim.msa.auth_service.domain.MemberAuthenticationInfo
import kr.dohoon_kim.msa.auth_service.domain.MemberAuthenticationInfoRepository
import kr.dohoon_kim.msa.auth_service.domain.MemberId
import kr.dohoon_kim.msa.auth_service.service.dto.AuthenticationSuccess
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class AuthenticationService(
    private val memberAuthenticationInfoRepository: MemberAuthenticationInfoRepository,
    private val jwtService: JWTService,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional(readOnly = true)
    fun nicknamePasswordAuthentication(nickname: String, password: String): AuthenticationSuccess {
        val memberAuthenticationInfo = memberAuthenticationInfoRepository.findByNickname(nickname)
            ?: throw UnauthorizedException(ServiceErrorCode.AUTHENTICATION_INFO_NOT_FOUND.code, "회원의 인증 정보가 존재하지 않습니다.")

        return if (passwordEncoder.matches(password, memberAuthenticationInfo.password)) {
            val accessToken = jwtService.createAccessToken(memberAuthenticationInfo.identifier)
            val refreshToken = jwtService.createRefreshToken(memberAuthenticationInfo.identifier)
            AuthenticationSuccess(accessToken = accessToken, refreshToken = refreshToken)
        } else {
            throw UnauthorizedException(ServiceErrorCode.AUTHENTICATION_INFO_NOT_FOUND.code, "회원의 인증 정보가 일치하지 않습니다.")
        }
    }

    @Transactional(readOnly = true)
    fun refreshAccessToken(refreshToken: String): AuthenticationSuccess {
        var memberId: MemberId = MemberId(UUID.randomUUID())

        try {
            val decodedRefreshToken = jwtService.verifyRefreshToken(refreshToken)
            memberId = MemberId(UUID.fromString(decodedRefreshToken.subject))
        } catch(e: JWTVerificationException) {
            throw UnauthorizedException(ServiceErrorCode.JWT_VERIFICATION_FAILED.code, "리프레시 토큰이 유효하지 않습니다.")
        }

        val memberInfo = memberAuthenticationInfoRepository.findById(memberId)
            ?: throw NotFoundException(ServiceErrorCode.AUTHENTICATION_INFO_NOT_FOUND.code, "회원의 인증 정보가 존재하지 않습니다.")
        val accessToken = jwtService.createAccessToken(memberId)

        return AuthenticationSuccess(accessToken = accessToken, refreshToken = refreshToken)
    }
}