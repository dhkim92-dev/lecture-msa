package kr.dohoon_kim.msa.auth_service.helper

import kr.dohoon_kim.msa.auth_service.configs.JwtConfiguration

class JwtHelper {

    companion object {
        fun createJwtConfigs(): JwtConfiguration {
            return JwtConfiguration(
                issuer = "test",
                accessTokenSecret = "test",
                accessTokenExpiration = 60000,
                refreshTokenSecret = "test",
                refreshTokenExpiration = 86400000
            )
        }
    }
}