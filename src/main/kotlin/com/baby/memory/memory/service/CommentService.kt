package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.response.CommentResponseDto

interface CommentService {
    fun createComment(req: CommentRequestDto)
    fun updateComment(req: CommentRequestDto)
    fun getComments(memoryId: Long): MutableList<CommentResponseDto>
    fun deleteComment(commentId: Long)
}