package com.baby.memory.member.service

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto

interface MemberService {
    fun signUp(req: MemberRequestDto)
    fun signIn(req: MemberRequestDto): TokenInfo
    fun getMyInfo(memberId: Long):MemberResponseDto
    // TODO : 비밀번호 변경
    fun updateMyInfo(memberId:Long, req: MemberUpdateRequestDto)
    fun addFollowing(memberId: Long, followedId: Long)
    // TODO : unFollowing
    // TODO : 유저 전체 조회 = 이름 / 팔로잉 수 number값 팔로워수 number값
    // TODO : 유저 조회
}