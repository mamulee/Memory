package com.baby.memory.memory.controller

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.dto.response.wrapper.MemoryWrapperDto
import com.baby.memory.memory.service.MemoryService
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
    fun getMemories():List<MemoryResponseDto>{
        return memoryService.getMemories()
    }

    @GetMapping("/self")
    fun getMyMemories():List<MemoryResponseDto>{
        return memoryService.getMyMemories()
    }

    @PostMapping("/new")
    fun createMemory(@RequestBody req: MemoryRequestDto) {
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