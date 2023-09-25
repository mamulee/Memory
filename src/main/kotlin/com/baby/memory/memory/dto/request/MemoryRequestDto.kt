package com.baby.memory.memory.dto.request

import com.baby.memory.memory.entity.Memory
import com.baby.memory.member.entity.Member

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