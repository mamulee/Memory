package com.baby.memory.memory.service.impl

import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.responses.error.exception.MemoryException
import com.baby.memory.common.responses.error.exception.MemoryExceptionType
import com.baby.memory.common.responses.success.MemorySuccessType
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.entity.SavedMemory
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.repository.SavedMemoryRepository
import com.baby.memory.memory.service.SavedMemoryService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SavedMemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository,
    private val savedMemoryRepository: SavedMemoryRepository
) : SavedMemoryService {
    override fun saveMemory(memberId: Long, memoryId: Long): MemorySuccessType {
        val savedMemory = savedMemoryRepository.findByMemberIdAndMemoryId(memberId, memoryId)
        return if (savedMemory == null) {
            val member = getMember(memberId)
            val memory = getMemory(memoryId)
            val newSavedMemory = SavedMemory(member, memory)
            savedMemoryRepository.save(newSavedMemory)
            MemorySuccessType.SAVE_MEMORY
        } else {
            savedMemoryRepository.delete(savedMemory)
            MemorySuccessType.UNSAVE_MEMORY
        }
    }

    override fun getMySavedMemories(memberId: Long, pageable: Pageable): List<MemoryResponseDto> {
        val self = getMember(memberId)
        return memoryRepository.findSavedMemoriesByMemberId(self, pageable)
            .map { MemoryResponseDto.of(it, true) }
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw MemoryException(MemoryExceptionType.NOT_FOUND_MEMORY)
}
