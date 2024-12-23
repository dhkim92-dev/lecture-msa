package kr.dohoon_kim.msa.auth_service.interfaces.dto

import com.fasterxml.jackson.annotation.JsonIgnore

interface LoginRequest {
    val principal: String
}