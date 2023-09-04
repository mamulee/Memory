package com.baby.memory.memory.dto.response

import com.baby.memory.memory.entity.Reaction
import com.baby.memory.memory.entity.enum.ReactionStatus

data class ReactionResponseDto(
    val memoryId: Long,
    val status: ReactionStatus
) {
    companion object{
        fun of(reaction: Reaction): ReactionResponseDto = ReactionResponseDto(
            memoryId = reaction.memory.id,
            status = reaction.status
        )
    }
}
