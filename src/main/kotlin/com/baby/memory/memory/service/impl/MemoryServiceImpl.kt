package com.baby.memory.memory.service.impl

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.responses.error.exception.MemoryException
import com.baby.memory.common.responses.error.exception.MemoryExceptionType
import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.service.MemoryService
import com.baby.memory.member.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository
): MemoryService {
    @Transactional
    override fun createMemory(memberId: Long, req: MemoryRequestDto) {
        val member = getMember(memberId)
        val memory = req.toEntity(member)
        memoryRepository.save(memory)
    }

    @Transactional
    override fun updateMemory(memoryId: Long, req: MemoryRequestDto): MemoryResponseDto {
        val memory = getMemory(memoryId)
        memory.content = req.content
        return MemoryResponseDto.of(memory)
    }

    @Transactional(readOnly = true)
    override fun getMemories(pageable: Pageable): Page<MemoryResponseDto> {
        return memoryRepository.findAll(pageable).map { MemoryResponseDto.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getMyMemories(pageable: Pageable): Page<MemoryResponseDto> {
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        getMember(memberId)
        return memoryRepository.findAllByMemberId(memberId, pageable)!!.map { MemoryResponseDto.of(it) }
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