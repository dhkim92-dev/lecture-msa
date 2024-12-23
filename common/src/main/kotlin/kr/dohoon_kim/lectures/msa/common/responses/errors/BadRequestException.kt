package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus

class BadRequestException(
    override val code: String= "BAD_REQUEST",
    override val message: String
) : BusinessException(HttpStatus.BAD_REQUEST, code, message) {

}