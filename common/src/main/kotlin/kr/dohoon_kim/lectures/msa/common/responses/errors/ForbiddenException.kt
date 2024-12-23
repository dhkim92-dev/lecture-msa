package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus

class ForbiddenException(
    override val code: String = "FORBIDDEN",
    override val message: String
): BusinessException(HttpStatus.FORBIDDEN, code, message) {
}