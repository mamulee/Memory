package com.baby.memory.memory.dto.request

import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.Memory

data class MemoryRequestDto(
    val content: String
) {
    fun toEntity(
        member: Member
    ): Memory = Memory(
        member,
        content
    )
}
