package com.baby.memory.memory.service.impl

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.service.MemoryService
import com.baby.memory.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository
): MemoryService {
    override fun createMemory(req: MemoryRequestDto) {
        val member = getMember(req.memberId)
        val memory = req.toEntity(member)
        memoryRepository.save(memory)
    }

    override fun updateMemory(req: MemoryRequestDto) {
        TODO("Not yet implemented")
    }

    override fun getMemory(memoryId: Long): MemoryResponseDto {
        TODO("Not yet implemented")
    }

    override fun getMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    override fun getMyMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    override fun getMySavedMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    override fun deleteMemory(memoryId: Long) {
        TODO("Not yet implemented")
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw Exception("해당 사용자를 찾을 수 없습니다.")
}