package kr.dohoon_kim.lectures.msa.common.responses

import org.slf4j.MDC
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


class DefaultResponseBodyAdvice: ResponseBodyAdvice<Any> {

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {

        val status = returnType.getMethodAnnotation(Envelop::class.java)!!.status
        val message = returnType.getMethodAnnotation(Envelop::class.java)!!.message
        response.setStatusCode(status)

        return ApiResponse<Any>(
            status = status.value(),
            message = message,
            payload = body
        )
    }

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.hasMethodAnnotation(Envelop::class.java)
    }
}