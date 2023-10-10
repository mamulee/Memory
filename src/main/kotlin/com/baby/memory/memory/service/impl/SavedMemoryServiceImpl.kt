package com.baby.memory.memory.service.impl

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.responses.error.exception.MemoryException
import com.baby.memory.common.responses.error.exception.MemoryExceptionType
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.dto.response.SavedMemoryResponseDto
import com.baby.memory.memory.entity.SavedMemory
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.repository.SavedMemoryRepository
import com.baby.memory.memory.service.SavedMemoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SavedMemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository,
    private val savedMemoryRepository: SavedMemoryRepository
) : SavedMemoryService {
    override fun addSavedMemory(memoryId: Long) {
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val member = getMember(memberId)
        val memory = getMemory(memoryId)
        // TODO: 중복 저장 금지. 이미 한 걸 쏘면 삭제하기를 합칠까ㅓ?
        val savedMemory = SavedMemory(member, memory)
        savedMemoryRepository.save(savedMemory)
    }

    override fun getMySavedMemories(pageable: Pageable): Page<SavedMemoryResponseDto> {
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        getMember(memberId)
        return savedMemoryRepository.findAllByMemberId(memberId, pageable)!!.map { SavedMemoryResponseDto.of(it) }
    }

    // TODO: 삭제 구현 다시 생각
    override fun deleteSavedMemory(memoryId: Long) {
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val member = getMember(memberId)
        val memory = getMemory(memoryId)
        val savedMemory = SavedMemory(member, memory)
        savedMemoryRepository.delete(savedMemory)
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw MemoryException(MemoryExceptionType.NOT_FOUND_MEMORY)
}
