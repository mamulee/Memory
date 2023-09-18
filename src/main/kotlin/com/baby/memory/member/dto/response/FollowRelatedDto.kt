package com.baby.memory.member.dto.response

import com.baby.memory.member.entity.Member
import com.baby.memory.memory.dto.response.CommentResponseDto

data class FollowRelatedDto(
    val id: Long,
    val memberName: String,

) {
    companion object {
        fun of(member: Member): FollowRelatedDto = FollowRelatedDto(
            id = member.id,
            memberName = member.memberName!!,
        )
    }
}
