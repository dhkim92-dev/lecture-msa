package kr.dohoon_kim.msa.auth_service.helper

import io.kotest.core.spec.style.AnnotationSpec
import jakarta.validation.Validation
import jakarta.validation.Validator

abstract class BaseAnnotationSpecUnitTest : AnnotationSpec(){

    protected lateinit var validator: Validator

    @BeforeAll
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

}