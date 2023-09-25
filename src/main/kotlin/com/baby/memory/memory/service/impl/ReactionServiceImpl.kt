package com.baby.memory.memory.service.impl

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.responses.error.exception.MemoryException
import com.baby.memory.common.responses.error.exception.MemoryExceptionType
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.request.ReactionRequestDto
import com.baby.memory.memory.dto.response.CommentResponseDto
import com.baby.memory.memory.entity.Reaction
import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.memory.repository.CommentRepository
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.repository.ReactionRepository
import com.baby.memory.memory.service.CommentService
import com.baby.memory.memory.service.ReactionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReactionServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository,
    private val reactionRepository: ReactionRepository
) : ReactionService {
    @Transactional
    override fun reaction(memoryId: Long, req: ReactionRequestDto) {
        val memory = getMemory(memoryId)
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val member = getMember(memberId)
        val reaction = req.toEntity(member, memory)

        reactionRepository.findByMemberIdAndMemoryId(memberId, memoryId)?.
            let { if(it.status == req.reactionStatus) it.status = ReactionStatus.NEUTRAL
                else it.status = req.reactionStatus
                } ?: reactionRepository.save(reaction)
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw MemoryException(MemoryExceptionType.NOT_FOUND_MEMORY)
}