package com.baby.memory.memory.dto.response

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.memory.entity.SavedMemory
import com.baby.memory.memory.entity.enum.ReactionStatus
import org.springframework.security.core.context.SecurityContextHolder

data class SavedMemoryResponseDto(
    val memoryId: Long,
    val memberId: Long,
    val content: String,
    val comments: List<CommentResponseDto>,
    val likeCnt: Int,
    val sadCnt: Int,
    val angryCnt: Int,
    val reactions: List<ReactionResponseDto>,
    val status: ReactionStatus
) {
    companion object {
        fun of(savedMemory: SavedMemory): SavedMemoryResponseDto = SavedMemoryResponseDto(
            memoryId = savedMemory.memory.id,
            memberId = savedMemory.member.id,
            content = savedMemory.memory.content,
            comments = savedMemory.memory.comments.map { CommentResponseDto.of(it) },
            likeCnt = savedMemory.memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.LIKE }.size,
            sadCnt = savedMemory.memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.SAD }.size,
            angryCnt = savedMemory.memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.ANGRY }.size,
            reactions = savedMemory.memory.reactions.map { ReactionResponseDto.of(it) },
            status = savedMemory.memory.reactions.firstOrNull {
                it.member.id == (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
            }?.status ?: ReactionStatus.NEUTRAL
        )
    }
}
