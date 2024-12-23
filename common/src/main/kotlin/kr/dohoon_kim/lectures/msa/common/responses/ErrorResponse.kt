package kr.dohoon_kim.lectures.msa.common.responses

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestCookieException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.support.MissingServletRequestPartException

class ErrorResponse(
    val code: String,
    val message: String? = null,
    var errors: List<FieldError> = listOf()
) {


    companion object {

        fun of(code: String, message: String?, errors: List<FieldError>) =
            ErrorResponse(code = code, message = message, errors = errors)

        fun of(code: ErrorCode) = ErrorResponse(code = code.code, message = code.message)

        fun of(code: ErrorCode, message: String?) =  ErrorResponse(code = code.code, message = message)

        fun of(e: MethodArgumentNotValidException) =
            ErrorResponse(code = "MethodArgumentNotValid", message = e.message ?: "", errors = FieldError.of(e.bindingResult))

        fun of(code: ErrorCode, violations: Set<ConstraintViolation<*>>) =
            ErrorResponse(code = code.code, message = code.message, errors = FieldError.of(violations))

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = e.value?.toString() ?: ""
            val errors = FieldError.of(e.name, value, e.errorCode)
            return ErrorResponse(code = "ArgumentTypeMismatch", message = e.value?.toString(), errors = errors)
        }

        fun of(e: ConstraintViolationException) =
            ErrorResponse(code = "ConstraintViolation", message = e.message, errors = FieldError.of(e.constraintViolations))

        fun of(e: MissingServletRequestParameterException) =
            ErrorResponse(code = "MissingServletRequestParameter", message = e.message, errors = FieldError.of(e.parameterName, "", reason = e.message?:""))

        fun of(e: MissingServletRequestPartException) =
            ErrorResponse(code = "MissingServletRequestPart", message = e.message, errors = FieldError.of(e.requestPartName, "", reason = e.message?:""))

        fun of(e: MissingRequestCookieException) =
                ErrorResponse(code = "MissingRequestCookie", message = e.message, errors = FieldError.of(e.cookieName, "", reason = e.message?:""))

        fun of(e: HttpRequestMethodNotSupportedException) =
            ErrorResponse(code = "HttpRequestMethodNotSupported", message = e.message, errors = emptyList())

        fun of(e: HttpMessageNotReadableException) =
            ErrorResponse(code = "HttpMessageNotReadable", message = e.message, errors = emptyList())
    }

    class FieldError(val field: String, val value: String, val reason: String?) {

        companion object {

            fun of(field: String, value: String, reason: String): List<FieldError> {
                return listOf(FieldError(field, value, reason))
            }

            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors: List<org.springframework.validation.FieldError> = bindingResult.fieldErrors

                return fieldErrors.stream().map {
                    FieldError(
                        it.field,
                        it.rejectedValue?.toString() ?: "",
                        it.defaultMessage ?: ""
                    )
                }.toList()
            }

            fun of(constraintViolations: Set<ConstraintViolation<*>>): List<FieldError> {
                return constraintViolations.toList()
                    .map { error ->
                        val invalidValue = error.invalidValue?.toString() ?: ""
                        val idx = error.messageTemplate.indexOf(".")
                        val propertyPath = error.messageTemplate.substring(idx + 1)
                        FieldError(propertyPath, invalidValue, error.message)
                    }
            }
        }
    }
}