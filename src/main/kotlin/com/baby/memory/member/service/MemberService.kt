package com.baby.memory.member.service

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberService {
    fun signUp(req: MemberRequestDto)
    fun signIn(req: MemberRequestDto): TokenInfo
    fun getMembers(pageable: Pageable): Page<MemberResponseDto>
    fun getMyInfo(memberId: Long): MemberResponseDto
    fun getMemberInfo(memberId: Long): MemberResponseDto
    fun updateMyInfo(memberId: Long, req: MemberUpdateRequestDto)
    fun addFollowing(memberId: Long, followedId: Long)
    // TODO : unFollowing
}