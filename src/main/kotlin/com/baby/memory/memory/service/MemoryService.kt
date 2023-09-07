package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto

interface MemoryService {
    fun createMemory(req: MemoryRequestDto)
    fun updateMemory(memoryId: Long, req: MemoryRequestDto): MemoryResponseDto
    // 페이징 필요
    fun getMemories(): List<MemoryResponseDto>
    fun getMyMemories(): List<MemoryResponseDto>
    fun getMySavedMemories(): List<MemoryResponseDto>
    fun deleteMemory(memoryId: Long)
}