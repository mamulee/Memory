package com.baby.memory.memory.dto.response

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.memory.entity.Memory
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
    val reactions: List<ReactionResponseDto>,
    val status: ReactionStatus,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(memory: Memory): MemoryResponseDto = MemoryResponseDto(
            memoryId = memory.id,
            memberId = memory.member.id,
            memberName = memory.member.memberName!!,
            content = memory.content,
            comments = memory.comments.map { CommentResponseDto.of(it) },
            likeCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.LIKE }.size,
            sadCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.SAD }.size,
            angryCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.ANGRY }.size,
            reactions = memory.reactions.map { ReactionResponseDto.of(it) },
            status = memory.reactions.firstOrNull {
                it.member.id == (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
            }?.status ?: ReactionStatus.NEUTRAL,
            createdAt = memory.createdAt
        )
    }
}
