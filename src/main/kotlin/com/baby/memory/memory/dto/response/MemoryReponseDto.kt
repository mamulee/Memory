package com.baby.memory.memory.dto.response

import com.baby.memory.memory.entity.Memory
import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.member.entity.Member

data class MemoryResponseDto(
    val memoryId: Long,
    val member: Member,
    val content: String,
    val comments: List<CommentResponseDto>,
    val likeCnt: Int,
    val sadCnt: Int,
    val angryCnt: Int
) {
    companion object {
        fun of(memory: Memory): MemoryResponseDto = MemoryResponseDto(
            memoryId = memory.id,
            member = memory.member,
            content = memory.content,
            comments = memory.comments.map { CommentResponseDto.of(it) },
            likeCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.LIKE }.size,
            sadCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.SAD }.size,
            angryCnt = memory.reactions.filter { ReactionResponseDto.of(it).status == ReactionStatus.ANGRY }.size
        )
    }
}
