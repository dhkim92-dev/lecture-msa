package kr.dohoon_kim.msa.auth_service.configs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration(
    @Value("\${jwt.access-token.secret}")
    val accessTokenSecret: String,
    @Value("\${jwt.refresh-token.secret}")
    val refreshTokenSecret: String,
    @Value("\${jwt.access-token.expiration}")
    val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token.expiration}")
    val refreshTokenExpiration: Long,
    @Value("\${jwt.issuer}")
    val issuer: String
) {

}