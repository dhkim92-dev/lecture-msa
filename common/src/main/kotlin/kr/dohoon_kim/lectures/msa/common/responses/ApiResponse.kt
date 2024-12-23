package kr.dohoon_kim.lectures.msa.common.responses

import org.slf4j.MDC


class ApiResponse<T>(
    val traceId: String? = MDC.get("traceId") ?: null,
    val status: Int,
    val payload: T?,
    val message: String = ""
) {

}