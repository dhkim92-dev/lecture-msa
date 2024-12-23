package kr.dohoon_kim.msa.auth_service.service.dto

data class AuthenticationSuccess(
    val type: String="Bearer",
    val accessToken: String,
    val refreshToken: String
)