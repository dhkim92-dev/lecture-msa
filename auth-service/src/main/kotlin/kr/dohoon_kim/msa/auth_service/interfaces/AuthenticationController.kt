package kr.dohoon_kim.msa.auth_service.interfaces

import jakarta.validation.Valid
import kr.dohoon_kim.lectures.msa.common.responses.Envelop
import kr.dohoon_kim.msa.auth_service.interfaces.dto.AuthenticationResponse
import kr.dohoon_kim.msa.auth_service.interfaces.dto.LoginRequest
import kr.dohoon_kim.msa.auth_service.interfaces.dto.NicknamePasswordLoginRequest
import kr.dohoon_kim.msa.auth_service.interfaces.dto.RefreshTokenRequest
import kr.dohoon_kim.msa.auth_service.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/nickname-password")
    @Envelop(status = HttpStatus.CREATED, message = "로그인 성공")
    fun login(@RequestBody @Valid request: NicknamePasswordLoginRequest): AuthenticationResponse {
        return AuthenticationResponse.of(authenticationService.nicknamePasswordAuthentication(request.nickname, request.password))
    }

    @PostMapping("/jwt/refresh-token")
    @Envelop(status = HttpStatus.CREATED, message = "토큰 재발급 성공")
    fun refresh(@RequestBody @Valid request: RefreshTokenRequest):AuthenticationResponse {
        return AuthenticationResponse.of(
            authenticationService.refreshAccessToken(request.refreshToken)
        )
    }
}