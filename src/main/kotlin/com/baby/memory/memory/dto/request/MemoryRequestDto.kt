package com.baby.memory.memory.dto.request

import com.baby.memory.memory.entity.Memory
import com.baby.memory.member.entity.Member

data class MemoryRequestDto(
    val memberId: Long,
    val memoryId: Long?,
    val content: String,
    val likeCnt: Int,
    val sadCnt: Int,
    val angryCnt: Int
) {
    fun toEntity(
        member: Member
    ): Memory = Memory(
        member,
        content
    )
}