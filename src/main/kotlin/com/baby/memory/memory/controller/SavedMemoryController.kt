package com.baby.memory.memory.controller

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.dto.response.SavedMemoryResponseDto
import com.baby.memory.memory.dto.response.wrapper.MemoryWrapperDto
import com.baby.memory.memory.service.MemoryService
import com.baby.memory.memory.service.SavedMemoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/saved-memories")
class SavedMemoryController(
    private val savedMemoryService: SavedMemoryService
) {
    @GetMapping("")
    fun getSavedMemories(pageable:Pageable):Page<SavedMemoryResponseDto>{
        // TODO : 검색 기능을 조회에 한방에 넣을지
        return savedMemoryService.getMySavedMemories(pageable)
    }

    @PostMapping("/{memoryId}")
    fun addSavedMemory(@PathVariable memoryId: Long) {
        savedMemoryService.addSavedMemory(memoryId)
    }

    @DeleteMapping("{memoryId}")
    fun deleteSavedMemory(
        @PathVariable memoryId: Long
    ) {
        savedMemoryService.deleteSavedMemory(memoryId)
    }
}