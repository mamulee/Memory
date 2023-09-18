package com.baby.memory.memory.service

import com.baby.memory.memory.dto.response.SavedMemoryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SavedMemoryService {
    fun addSavedMemory(memoryId: Long)
    fun getMySavedMemories(pageable: Pageable): Page<SavedMemoryResponseDto>
    fun deleteSavedMemory(memoryId: Long)
}