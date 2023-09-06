package com.baby.memory.memory.service.impl

import com.baby.memory.memory.dto.request.MemoryRequestDto
import com.baby.memory.memory.dto.response.MemoryResponseDto
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.service.MemoryService
import com.baby.memory.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoryServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository
): MemoryService {
    @Transactional
    override fun createMemory(req: MemoryRequestDto) {
        val member = getMember(req.memberId)
        val memory = req.toEntity(member)
        memoryRepository.save(memory)
    }

    @Transactional
    override fun updateMemory(req: MemoryRequestDto) {
        val memory = getMemory(req.memoryId!!)
        memory.content = req.content
    }

    @Transactional(readOnly = true)
    override fun getMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getMyMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getMySavedMemories(): MutableList<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    override fun deleteMemory(memoryId: Long) {
        val memory = getMemory(memoryId)
        memory.isDeleted = true
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw Exception("해당 사용자를 찾을 수 없습니다.")
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw Exception("해당 게시글을 찾을 수 없습니다.")
}