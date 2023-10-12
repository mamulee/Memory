package com.baby.memory.memory.service

import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.memory.dto.response.MemoryResponseDto
import org.springframework.data.domain.Pageable

interface SavedMemoryService {
    fun saveMemory(memberId: Long, memoryId: Long): MemorySuccessType
    fun getMySavedMemories(memberId: Long, pageable: Pageable): List<MemoryResponseDto>
}
