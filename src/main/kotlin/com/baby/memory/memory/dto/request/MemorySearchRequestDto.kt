package com.baby.memory.memory.dto.request

import com.baby.memory.memory.entity.Memory
import com.baby.memory.member.entity.Member

data class MemorySearchRequestDto(
    val keyword: String?
)