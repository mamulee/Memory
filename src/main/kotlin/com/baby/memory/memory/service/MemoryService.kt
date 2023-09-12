package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemoryService {
    fun createMemory(req: MemoryRequestDto)
    fun updateMemory(memoryId: Long, req: MemoryRequestDto): MemoryResponseDto
    // 페이징 필요
    fun getMemories(pageable: Pageable): Page<MemoryResponseDto>
    fun getMyMemories(): List<MemoryResponseDto>
    fun getMySavedMemories(): List<MemoryResponseDto>
    fun deleteMemory(memoryId: Long)
}