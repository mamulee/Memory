package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemoryService {
    fun createMemory(memberId: Long, req: MemoryRequestDto)
    fun updateMemory(memoryId: Long, req: MemoryRequestDto): MemoryResponseDto
    fun getMemories(pageable: Pageable): Page<MemoryResponseDto>
    fun getMyMemories(pageable: Pageable): Page<MemoryResponseDto>
    fun deleteMemory(memoryId: Long)
}