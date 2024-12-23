package kr.dohoon_kim.msa.auth_service.codes

enum class ServiceErrorCode(val code: String){

    AUTHENTICATION_INFO_NOT_FOUND("AUTH-ERROR-0001"),
    AUTHENTICATION_INFO_NOT_MATCH("AUTH-ERROR-0002"),
    JWT_VERIFICATION_FAILED("AUTH-ERROR-0003")
}