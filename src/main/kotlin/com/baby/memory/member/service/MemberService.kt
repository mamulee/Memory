package com.baby.memory.member.service

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberSearchRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto
import org.springframework.data.domain.Pageable

interface MemberService {
    fun signUp(req: MemberRequestDto)
    fun signIn(req: MemberRequestDto): TokenInfo
    fun getMembers(req: MemberSearchRequestDto, pageable: Pageable): List<MemberResponseDto>
    fun getMyInfo(memberId: Long): MemberResponseDto
    fun getMemberInfo(memberId: Long, selfId: Long): MemberResponseDto
    fun getMemberInfoByMemberName(memberName: String, selfId: Long): MemberResponseDto
    fun updateMyInfo(memberId: Long, req: MemberUpdateRequestDto)
    fun toggleFollowing(memberId: Long, followedId: Long)
}
