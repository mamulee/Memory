package com.baby.memory.memory.service.impl

import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.responses.error.exception.MemoryException
import com.baby.memory.common.responses.error.exception.MemoryExceptionType
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.service.MemoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository
) : MemoryService {
    @Transactional
    override fun createMemory(memberId: Long, req: MemoryRequestDto) {
        val member = getMember(memberId)
        val memory = req.toEntity(member)
        memoryRepository.save(memory)
    }

    @Transactional
    override fun updateMemory(memberId: Long, memoryId: Long, req: MemoryRequestDto): MemoryResponseDto {
        val self = getMember(memberId)
        val memory = getMemory(memoryId)
        memory.content = req.content
        return MemoryResponseDto.of(memory, self.savedMemories.any { it.memory.id == memoryId })
    }

    @Transactional(readOnly = true)
    override fun getMemories(memberId: Long, pageable: Pageable, req: MemorySearchRequestDto): List<MemoryResponseDto> {
        val self = getMember(memberId)
        return memoryRepository.getMemoriesWithSearch(pageable, req)
            .map { memory -> MemoryResponseDto.of(memory, self.savedMemories.any { it.memory.id == memory.id }) }
    }

    @Transactional(readOnly = true)
    override fun getMyMemories(memberId: Long, pageable: Pageable): Page<MemoryResponseDto> {
        val self = getMember(memberId)
        return memoryRepository.findAllByMemberId(memberId, pageable)!!
            .map { memory -> MemoryResponseDto.of(memory, self.savedMemories.any { it.memory.id == memory.id }) }
    }

    @Transactional
    override fun deleteMemory(memoryId: Long) {
        val memory = getMemory(memoryId)
        memory.isDeleted = true
        // 게시글에 포함된 댓글도 모두 isDeleted = true 처리 필요.
        memory.comments.map { it.isDeleted = true }
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw MemoryException(MemoryExceptionType.NOT_FOUND_MEMORY)
}
