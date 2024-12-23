package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus

class ConflictException(
    override val code: String = "CONFLICT",
    override val message: String
): BusinessException(HttpStatus.CONFLICT, code, message) {

}