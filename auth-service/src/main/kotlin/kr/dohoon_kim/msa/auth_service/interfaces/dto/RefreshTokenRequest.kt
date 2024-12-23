package kr.dohoon_kim.msa.auth_service.interfaces.dto

import jakarta.validation.constraints.NotEmpty


data class RefreshTokenRequest(
    val type: String = "Bearer",
    @field: NotEmpty(message = "Refresh token must not be empty")
    val refreshToken: String
) {

}