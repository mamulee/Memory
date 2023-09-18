package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.request.ReactionRequestDto
import com.baby.memory.memory.dto.response.CommentResponseDto

interface ReactionService {
    fun reaction(memoryId: Long, req:ReactionRequestDto)
}