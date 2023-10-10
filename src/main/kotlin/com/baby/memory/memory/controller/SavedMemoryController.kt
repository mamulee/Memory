package com.baby.memory.memory.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.service.SavedMemoryService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/saved-memories")
class SavedMemoryController(
    private val resourceValidator: ResourceValidator,
    private val savedMemoryService: SavedMemoryService
) {
    @GetMapping("")
    fun getSavedMemories(
        @SortDefault(sort = ["memoryId"], direction = Sort.Direction.DESC)
        pageable: Pageable
    ): ResponseEntity<SuccessResponse> {
        // TODO : 검색 기능을 조회에 한방에 넣을지
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_SAVED_MEMORY,
            savedMemoryService.getMySavedMemories(pageable)
        )
    }

    @PostMapping("{memoryId}")
    fun saveMemory(
        @PathVariable memoryId: Long
    ): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            savedMemoryService.saveMemory(memberId, memoryId)
        )
    }
}
