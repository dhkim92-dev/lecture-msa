package kr.dohoon_kim.msa.auth_service.domain

import com.fasterxml.jackson.databind.ser.Serializers.Base
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.UUID

@Entity
class MemberAuthenticationInfo(
    id: MemberId? = null,
    nickname: String = "",
    password: String = ""
): BaseEntity<MemberId>(id) {

    @Column
    var nickname: String = nickname
        private set

    @Column
    var password: String = password
        private set

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changePassword(password: String) {
        this.password = password
    }
}