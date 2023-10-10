package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemoryService {
    fun createMemory(memberId: Long, req: MemoryRequestDto)
    fun updateMemory(memberId: Long, memoryId: Long, req: MemoryRequestDto): MemoryResponseDto
    fun getMemories(memberId: Long, pageable: Pageable, req: MemorySearchRequestDto): List<MemoryResponseDto>
    fun getMyMemories(memberId: Long, pageable: Pageable): Page<MemoryResponseDto>
    fun deleteMemory(memoryId: Long)
}
