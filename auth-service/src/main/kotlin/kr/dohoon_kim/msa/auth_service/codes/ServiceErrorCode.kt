package kr.dohoon_kim.msa.auth_service.codes

import kr.dohoon_kim.lectures.msa.common.responses.ErrorCode
import org.springframework.http.HttpStatus


enum class ServiceErrorCode(
    override val code: String,
    override val message: String)
    : ErrorCode {

    AUTHENTICATION_INFO_NOT_FOUND("AE-0001", "인증 정보가 존재하지 않습니다"),
    AUTHENTICATION_INFO_NOT_MATCH("AE-0002", "인증 정보가 일치하지 않습니다"),
    JWT_VERIFICATION_FAILED("AE-0003", "유효하지 않은 JWT 입니다")
}