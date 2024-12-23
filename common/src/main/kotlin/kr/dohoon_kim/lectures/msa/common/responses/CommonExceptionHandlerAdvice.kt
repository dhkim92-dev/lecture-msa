package kr.dohoon_kim.lectures.msa.common.responses

import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestCookieException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import kr.dohoon_kim.lectures.msa.common.responses.errors.BusinessException

@RestControllerAdvice
open class CommonExceptionHandlerAdvice {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(e: MethodArgumentTypeMismatchException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("MethodArgumentTypeMismatchException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("MethodArgumentNotValidException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationExceptionHandler(e: ConstraintViolationException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("ConstraintViolationException occured: ${e.message}")
        val response = ErrorResponse.of(e);
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(e: MissingServletRequestParameterException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("MissingServletRequestParameterException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(MissingServletRequestPartException::class)
    fun missingServletRequestPartExceptionHandler(e: MissingServletRequestPartException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("MissingServletRequestPartException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(MissingRequestCookieException::class)
    fun missingRequestCookieExceptionHandler(e: MissingRequestCookieException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("MissingRequestCookieException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("HttpRequestMethodNotSupportedException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException)
            : ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("HttpMessageNotReadableException occured: ${e.message}")
        val response = ErrorResponse.of(e)
        return makeResponseEntity(HttpStatus.BAD_REQUEST, response)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("BusinessException status : ${e.status.name} code : ${e.code} message : ${e.message}")
        return makeResponseEntity(e.status, ErrorResponse.Companion.of(e))
    }

    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseEntity<ApiResponse<ErrorResponse>> {
        logger.error("Exception occured: ${e.message}")
        val response = ErrorResponse(
            code = "INTERNAL_SERVER_ERROR",
            message = e.message
        )
        return makeResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response)
    }

    private fun makeResponseEntity(status: HttpStatus, errorResponse: ErrorResponse)
    : ResponseEntity<ApiResponse<ErrorResponse>> {
        return ResponseEntity.status(status).body(
            ApiResponse<ErrorResponse>(
                traceId = MDC.get("traceId")?:null,
                status = status.value(),
                payload = errorResponse,
                message = errorResponse.message?:""
            )
        )
    }
}