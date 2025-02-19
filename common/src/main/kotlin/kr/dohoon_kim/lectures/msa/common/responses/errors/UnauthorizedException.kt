package kr.dohoon_kim.lectures.msa.common.responses.errors

import kr.dohoon_kim.lectures.msa.common.responses.ErrorCode
import org.springframework.http.HttpStatus

class UnauthorizedException(
    override val code: String = "UNAUTHORIZED",
    override val message: String = ""
): BusinessException(HttpStatus.UNAUTHORIZED, code, message){

    constructor(e: ErrorCode): this(e.code, e.message){
    }
}