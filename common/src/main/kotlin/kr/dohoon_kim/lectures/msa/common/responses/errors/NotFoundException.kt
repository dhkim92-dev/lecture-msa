package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus

class NotFoundException(
    override val code: String = "NOT_FOUND",
    override val message: String
): BusinessException(HttpStatus.NOT_FOUND, code, message) {
}