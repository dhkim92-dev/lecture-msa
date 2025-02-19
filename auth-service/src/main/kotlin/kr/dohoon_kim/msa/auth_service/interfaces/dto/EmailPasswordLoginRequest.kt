package kr.dohoon_kim.msa.auth_service.interfaces.dto

import jakarta.validation.constraints.NotEmpty

class EmailPasswordLoginRequest(
    @field: NotEmpty(message = "이메일은 필수입니다.")
    val email: String,
    @field: NotEmpty(message = "패스워드는 필수입니다.")
    val password: String
) {

}