package com.baby.memory.member.dto.request

import com.baby.memory.member.entity.Member

data class MemberUpdateRequestDto(
    val memberPassword: String?,
    val memberName: String?,
) {
}
