package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.response.CommentResponseDto

interface CommentService {
    fun createComment(memberId: Long, memoryId: Long, req: CommentRequestDto)
    fun updateComment(memoryId: Long, commentId: Long, req: CommentRequestDto)
    fun getComments(memoryId: Long): List<CommentResponseDto>
    fun deleteComment(memoryId: Long, commentId: Long)
}
