package kr.dohoon_kim.lectures.msa.common.responses.errors

import org.springframework.http.HttpStatus
import kr.dohoon_kim.lectures.msa.common.responses.ErrorCode


open class BusinessException(
    override val status: HttpStatus,
    override val code: String,
    override val message: String
) : RuntimeException(message), ErrorCode {

}