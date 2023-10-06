package com.baby.memory.memory.controller

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.dto.response.wrapper.MemoryWrapperDto
import com.baby.memory.memory.service.MemoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
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
@RequestMapping("/memories")
class MemoryController(
    private val resourceValidator: ResourceValidator,
    private val memoryService: MemoryService
) {
    @GetMapping("")
    fun getMemories(
        pageable: Pageable,
        @RequestBody req: MemorySearchRequestDto
    ): ResponseEntity<SuccessResponse> {
        // TODO : 검색 기능을 조회에 한방에 넣을지
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_MEMORY,
            memoryService.getMemories(pageable, req)
        )
    }

    @GetMapping("/self")
    fun getMyMemories(pageable: Pageable): ResponseEntity<SuccessResponse> {
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.GET_MY_MEMORY,
            memoryService.getMyMemories(pageable)
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
        resourceValidator.validateMember(memoryId, 'M')
        return SuccessResponse.toResponseEntity(
            MemorySuccessType.UPDATE_MEMORY,
            MemoryWrapperDto.of(memoryService.updateMemory(memoryId, req))
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