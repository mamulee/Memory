package com.baby.memory.member.service

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto

interface MemberService {
    fun signUp(req: MemberRequestDto)
    fun signIn(req: MemberRequestDto): TokenInfo
}