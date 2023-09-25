package com.baby.memory.common.service

import com.baby.memory.common.dto.CustomUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationFacadeImpl: AuthenticationFacade {
    override fun getAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }

    override fun getPrincipal(): CustomUser {
        return getAuthentication().principal as CustomUser
    }
}