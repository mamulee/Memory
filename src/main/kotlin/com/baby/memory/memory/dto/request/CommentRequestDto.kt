package com.baby.memory.memory.dto.request

import com.baby.memory.memory.entity.Memory
import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.Comment

data class CommentRequestDto(
    val memberId: Long,
    val memoryId: Long,
    val commentId: Long?,
    val content: String
) {
    fun toEntity(member: Member, memory: Memory): Comment = Comment(member, memory, content)
}