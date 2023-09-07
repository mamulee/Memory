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
    override fun updateMemory(memoryId: Long, req: MemoryRequestDto): MemoryResponseDto {
        val memory = getMemory(memoryId)
        memory.content = req.content
        return MemoryResponseDto.of(memory)
    }

    @Transactional(readOnly = true)
    override fun getMemories(): List<MemoryResponseDto> {
        return memoryRepository.findAll().map { MemoryResponseDto.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getMyMemories(): List<MemoryResponseDto> {
        return memoryRepository.findAllByMemberId(1L)!!.map { MemoryResponseDto.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getMySavedMemories(): List<MemoryResponseDto> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteMemory(memoryId: Long) {
        val memory = getMemory(memoryId)
        memory.isDeleted = true
        // 게시글에 포함된 댓글도 모두 isDeleted = true 처리 필요.
        memory.comments.map { it.isDeleted = true }
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw Exception("해당 사용자를 찾을 수 없습니다.")
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw Exception("해당 게시글을 찾을 수 없습니다.")
}