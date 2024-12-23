package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus

class UnauthorizedException(
    override val code: String = "UNAUTHORIZED",
    override val message: String
): BusinessException(HttpStatus.UNAUTHORIZED, code, message){
}