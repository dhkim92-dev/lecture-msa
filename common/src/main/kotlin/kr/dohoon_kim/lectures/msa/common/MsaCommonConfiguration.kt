package kr.dohoon_kim.lectures.msa.common

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import kr.dohoon_kim.lectures.msa.common.responses.CommonExceptionHandlerAdvice
import org.slf4j.LoggerFactory

@AutoConfiguration
@ConditionalOnProperty(prefix = "msa.auto-configuration", name = ["enabled"], havingValue = "true", matchIfMissing = true)
class MsaCommonConfiguration {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    @ConditionalOnMissingBean(CommonExceptionHandlerAdvice::class)
    fun commonExceptionHandlerAdvice(): CommonExceptionHandlerAdvice {
        logger.info("CommonExceptionHandlerAdvice bean created")
        return CommonExceptionHandlerAdvice()
    }
}