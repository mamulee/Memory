package com.baby.memory.memory.repository

import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.entity.Memory
import org.springframework.data.domain.Pageable

interface CustomMemoryRepository {
    fun getMemoriesWithSearch(pageable: Pageable, req: MemorySearchRequestDto): List<Memory>
}
