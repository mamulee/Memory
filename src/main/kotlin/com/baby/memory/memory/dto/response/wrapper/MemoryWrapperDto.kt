package com.baby.memory.memory.dto.response.wrapper

import com.baby.memory.memory.dto.response.MemoryResponseDto

data class MemoryWrapperDto(
    val memoryResponseDto: MemoryResponseDto
){
    companion object{
        fun of(memoryResponseDto: MemoryResponseDto): MemoryWrapperDto {
            return MemoryWrapperDto(memoryResponseDto)
        }
    }
}
