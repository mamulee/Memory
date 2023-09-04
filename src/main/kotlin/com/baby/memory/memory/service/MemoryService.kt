package com.baby.memory.memory.service

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto

interface MemoryService {
    fun createMemory(req: MemoryRequestDto)
    fun updateMemory(req: MemoryRequestDto)
    fun getMemory(memoryId: Long): MemoryResponseDto // 이건...필요 없을 듯? 상세보기가 없을테니까.
    // 페이징 필요
    fun getMemories(): MutableList<MemoryResponseDto>
    fun getMyMemories(): MutableList<MemoryResponseDto>
    fun getMySavedMemories(): MutableList<MemoryResponseDto>
    fun deleteMemory(memoryId: Long)
}