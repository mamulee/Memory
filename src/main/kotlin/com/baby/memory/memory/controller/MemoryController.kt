package com.baby.memory.memory.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.dto.response.wrapper.MemoryWrapperDto
import com.baby.memory.memory.service.MemoryService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memories")
class MemoryController(
    private val resourceValidator: ResourceValidator,
    private val memoryService: MemoryService
) {
    @PostMapping("")
    fun getMemories(
        pageable: Pageable,
        @RequestBody req: MemorySearchRequestDto
    ): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_MEMORY,
            memoryService.getMemories(memberId, pageable, req)
        )
    }

    @GetMapping("/self")
    fun getMyMemories(@SortDefault(sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_MY_MEMORY,
            memoryService.getMyMemories(memberId, pageable)
        )
    }

    @PostMapping("/new")
    fun createMemory(@RequestBody req: MemoryRequestDto): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        memoryService.createMemory(memberId, req)
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.CREATE_MEMORY
        )
    }

    @PatchMapping("{memoryId}")
    fun updateMemory(
        @PathVariable memoryId: Long,
        @RequestBody req: MemoryRequestDto
    ): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.validateMember(memoryId, 'M')
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.UPDATE_MEMORY,
            MemoryWrapperDto.of(memoryService.updateMemory(memberId, memoryId, req))
        )
    }

    @DeleteMapping("{memoryId}")
    fun deleteMemory(
        @PathVariable memoryId: Long
    ): ResponseEntity<SuccessResponse> {
        resourceValidator.validateMember(memoryId, 'M')
        memoryService.deleteMemory(memoryId)
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.DELETE_MEMORY
        )
    }
}
