package kr.dohoon_kim.lectures.msa.common.responses

import org.springframework.http.HttpStatus

interface ErrorCode {
    val status: HttpStatus
    val code: String
    val message: String
}