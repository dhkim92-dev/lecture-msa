package kr.dohoon_kim.msa.auth_service.interfaces.dto

import kr.dohoon_kim.msa.auth_service.service.dto.AuthenticationSuccess

data class AuthenticationResponse(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String
) {

    companion object {

        fun of(result: AuthenticationSuccess): AuthenticationResponse {
            return AuthenticationResponse(
                tokenType = result.type,
                accessToken = result.accessToken,
                refreshToken = result.refreshToken
            )
        }
    }
}