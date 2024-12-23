package kr.dohoon_kim.msa.auth_service.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import kr.dohoon_kim.msa.auth_service.configs.JwtConfiguration
import kr.dohoon_kim.msa.auth_service.domain.MemberId
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class JWTService(private val jwtConfiguration: JwtConfiguration) {

    private val accessTokenAlgorithm = Algorithm.HMAC256(jwtConfiguration.accessTokenSecret)

    private val refreshTokenAlgorithm = Algorithm.HMAC256(jwtConfiguration.refreshTokenSecret)

    fun createAccessToken(memberId: MemberId): String {
        val now = Instant.now()
        val expiration = now.plusMillis(jwtConfiguration.accessTokenExpiration)

        return JWT.create()
            .withIssuer(jwtConfiguration.issuer)
            .withSubject(memberId.id.toString())
            .withExpiresAt(Date.from(expiration))
            .sign(accessTokenAlgorithm)
    }

    fun createRefreshToken(memberId: MemberId): String {
        val now = Instant.now()
        val expiration = now.plusMillis(jwtConfiguration.refreshTokenExpiration)

        return JWT.create()
            .withIssuer(jwtConfiguration.issuer)
            .withSubject(memberId.id.toString())
            .withExpiresAt(Date.from(expiration))
            .sign(refreshTokenAlgorithm)
    }

    fun verifyAccessToken(token: String): DecodedJWT {
        return JWT.require(accessTokenAlgorithm)
            .withIssuer(jwtConfiguration.issuer)
            .build()
            .verify(token)
    }

    fun verifyRefreshToken(token: String): DecodedJWT {
        return JWT.require(refreshTokenAlgorithm)
            .withIssuer(jwtConfiguration.issuer)
            .build()
            .verify(token)
    }
}