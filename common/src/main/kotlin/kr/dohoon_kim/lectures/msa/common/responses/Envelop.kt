package kr.dohoon_kim.lectures.msa.common.responses

import org.springframework.http.HttpStatus

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Envelop(val status: HttpStatus, val message: String = "Success") {

}