package com.baby.memory.member.dto.request

import com.baby.memory.member.entity.Member

data class MemberUpdateRequestDto(
    //TODO: 내 정보 변경을 비밀번호 / 닉네임 API 따로 만들지
    val memberPassword: String?,
    val memberName: String?,
) {
}
