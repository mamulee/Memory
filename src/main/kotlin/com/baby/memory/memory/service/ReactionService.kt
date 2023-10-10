package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.ReactionRequestDto

interface ReactionService {
    fun reaction(memoryId: Long, memberId: Long, req: ReactionRequestDto)
}
