package com.baby.memory.memory.dto.response

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.memory.entity.Memory
import com.baby.memory.memory.entity.SavedMemory
import com.baby.memory.memory.entity.enum.ReactionStatus
import org.springframework.security.core.context.SecurityContextHolder
import java.time.LocalDateTime

data class MemoryResponseDto(
    val memoryId: Long,
    val memberId: Long,
    val memberName: String,
    val content: String,
    val comments: List<CommentResponseDto>,
    val likeCnt: Int,
    val sadCnt: Int,
    val angryCnt: Int,
    val reactionStatus: ReactionStatus,
    val isSaved: Boolean,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(memory: Memory, isSaved: Boolean): MemoryResponseDto = MemoryResponseDto(
            memoryId = memory.id,
            memberId = memory.member.id,
            memberName = memory.member.memberName!!,
            content = memory.content,
            comments = memory.comments.map { CommentResponseDto.of(it) },
            likeCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.LIKE }.size,
            sadCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.SAD }.size,
            angryCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.ANGRY }.size,
            reactionStatus = memory.reactions.firstOrNull {
                it.member.id == (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
            }?.status ?: ReactionStatus.NEUTRAL,
            isSaved = isSaved,
            createdAt = memory.createdAt
        )
        fun of(savedMemory: SavedMemory, isSaved: Boolean): MemoryResponseDto = MemoryResponseDto(
            memoryId = savedMemory.memory.id,
            memberId = savedMemory.memory.member.id,
            memberName = savedMemory.memory.member.memberName!!,
            content = savedMemory.memory.content,
            comments = savedMemory.memory.comments.map { CommentResponseDto.of(it) },
            likeCnt = savedMemory.memory.reactions
                .filter { ReactionResponseDto.of(it).status == ReactionStatus.LIKE }.size,
            sadCnt = savedMemory.memory.reactions
                .filter { ReactionResponseDto.of(it).status == ReactionStatus.SAD }.size,
            angryCnt = savedMemory.memory.reactions
                .filter { ReactionResponseDto.of(it).status == ReactionStatus.ANGRY }.size,
            reactionStatus = savedMemory.memory.reactions.firstOrNull {
                it.member.id == (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
            }?.status ?: ReactionStatus.NEUTRAL,
            isSaved = isSaved,
            createdAt = savedMemory.memory.createdAt
        )
    }
}
