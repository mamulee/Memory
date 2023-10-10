package com.baby.memory.memory.controller

import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.service.SavedMemoryService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/saved-memories")
class SavedMemoryController(
    private val savedMemoryService: SavedMemoryService
) {
    @GetMapping("")
    fun getSavedMemories(pageable: Pageable): ResponseEntity<SuccessResponse> {
        // TODO : 검색 기능을 조회에 한방에 넣을지
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_SAVED_MEMORY,
            savedMemoryService.getMySavedMemories(pageable)
        )
    }

    @PostMapping("/{memoryId}")
    fun addSavedMemory(@PathVariable memoryId: Long): ResponseEntity<SuccessResponse> {
        savedMemoryService.addSavedMemory(memoryId)
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.SAVE_MEMORY
        )
    }

    @DeleteMapping("{memoryId}")
    fun deleteSavedMemory(
        @PathVariable memoryId: Long
    ): ResponseEntity<SuccessResponse> {
        savedMemoryService.deleteSavedMemory(memoryId)
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.UNSAVE_MEMORY
        )
    }
}
