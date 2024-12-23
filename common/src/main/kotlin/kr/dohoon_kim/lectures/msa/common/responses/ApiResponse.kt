package kr.dohoon_kim.lectures.msa.common.responses


class ApiResponse<T>(
    val traceId: String? = null,
    val status: Int,
    val data: T?,
    val message: String = ""
) {

}