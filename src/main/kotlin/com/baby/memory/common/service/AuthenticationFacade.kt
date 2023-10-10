package com.baby.memory.common.service

import com.baby.memory.common.dto.CustomUser
import org.springframework.security.core.Authentication

interface AuthenticationFacade {
    fun getAuthentication(): Authentication
    fun getPrincipal(): CustomUser
}
