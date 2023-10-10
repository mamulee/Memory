package com.baby.memory.memory.dto.request

import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.Comment
import com.baby.memory.memory.entity.Memory

data class CommentRequestDto(
    val content: String
) {
    fun toEntity(member: Member, memory: Memory): Comment = Comment(member, memory, content)
}
