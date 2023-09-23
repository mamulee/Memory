package com.baby.memory.memory.controller

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.dto.response.wrapper.MemoryWrapperDto
import com.baby.memory.memory.service.MemoryService
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
@RequestMapping("/memories")
class MemoryController(
    private val memoryService: MemoryService
) {
    @GetMapping("")
    fun getMemories(pageable:Pageable):Page<MemoryResponseDto>{
        // TODO : 검색 기능을 조회에 한방에 넣을지
        return memoryService.getMemories(pageable)
    }

    @GetMapping("/self")
    fun getMyMemories(pageable:Pageable):Page<MemoryResponseDto>{
        return memoryService.getMyMemories(pageable)
    }

    @PostMapping("/new")
    fun createMemory(@RequestBody req: MemoryRequestDto) {
        //TODO : memberId 안 받는 거로 (토큰 해결)
        memoryService.createMemory(req)
    }

    @PatchMapping("{memoryId}")
    fun updateMemory(
        @PathVariable memoryId: Long,
        @RequestBody req: MemoryRequestDto
    ): MemoryWrapperDto {
        return MemoryWrapperDto.of(memoryService.updateMemory(memoryId, req))
    }

    @DeleteMapping("{memoryId}")
    fun deleteMemory(
        @PathVariable memoryId: Long
    ) {
        memoryService.deleteMemory(memoryId)
    }
}