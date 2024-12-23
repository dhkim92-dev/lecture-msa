package kr.dohoon_kim.msa.auth_service.interfaces.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotEmpty

class NicknamePasswordLoginRequest(
    @field: NotEmpty(message = "닉네임은 필수입니다.")
    val nickname: String,
    @field: NotEmpty(message = "패스워드는 필수입니다.")
    val password: String
) {

}