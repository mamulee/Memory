package com.baby.memory.memory.dto.response

import com.baby.memory.memory.entity.Comment

data class CommentResponseDto(
    val memberId: Long,
    val memberName: String,
    val memoryId: Long,
    val commentId: Long,
    val content: String,
    val isDeleted: Boolean
) {
    companion object {
        fun of(comment: Comment): CommentResponseDto = CommentResponseDto(
            memberId = comment.member.id,
            memberName = comment.member.memberName,
            memoryId = comment.memory.id,
            commentId = comment.id,
            content = comment.content,
            isDeleted = comment.isDeleted
        )
    }
}
