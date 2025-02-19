package kr.dohoon_kim.msa.auth_service.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import kr.dohoon_kim.lectures.msa.common.responses.errors.UnauthorizedException
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@Entity
class MemberAuthenticationInfo(
    id: UUID? = null,
    email: String = "",
    password: String = ""
): UUIDEntity(id) {

    @Column(unique = true, length = 64)
    var email: String = email
        private set

    @Column(length = 255)
    var password: String = password
        private set

    fun changePassword(current: String, newPassword: String, passwordEncoder: PasswordEncoder) {
        if(!passwordEncoder.matches(current, password)) {
            throw UnauthorizedException()
        }

        this.password = password
    }
}