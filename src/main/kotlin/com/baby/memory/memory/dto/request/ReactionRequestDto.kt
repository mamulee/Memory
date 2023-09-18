package com.baby.memory.memory.dto.request

import com.baby.memory.memory.entity.Memory
import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.Comment
import com.baby.memory.memory.entity.Reaction
import com.baby.memory.memory.entity.enum.ReactionStatus

data class ReactionRequestDto(
    val reactionStatus: ReactionStatus
) {
    fun toEntity(member: Member, memory: Memory) = Reaction(
        member,
        memory,
        reactionStatus
    )
}