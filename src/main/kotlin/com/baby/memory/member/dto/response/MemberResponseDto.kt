package com.baby.memory.member.dto.response

import com.baby.memory.member.entity.Member

data class MemberResponseDto(
    val id: Long,
    val memberEmail: String,
    val memberName: String
) {
    companion object {
        fun of(member: Member): MemberResponseDto = MemberResponseDto(
            id = member.id,
            memberEmail = member.memberEmail,
            memberName = member.memberName!!
        )
    }
}
