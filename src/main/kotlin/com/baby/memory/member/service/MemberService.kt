package com.baby.memory.member.service

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto

interface MemberService {
    fun signUp(req: MemberRequestDto)
    fun signIn(req: MemberRequestDto): TokenInfo
    fun getMyInfo(memberId: Long):MemberResponseDto
    fun updateMyInfo(memberId:Long, req: MemberUpdateRequestDto)
    fun addFollowing(memberId: Long, followedId: Long)
}