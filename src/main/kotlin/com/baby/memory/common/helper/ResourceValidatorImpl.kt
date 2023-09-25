package com.baby.memory.common.helper

import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.service.AuthenticationFacade
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.repository.CommentRepository
import com.baby.memory.memory.repository.MemoryRepository
import org.springframework.stereotype.Component

@Component
class ResourceValidatorImpl(
    private val authenticationFacade: AuthenticationFacade,
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository,
    private val commentRepository: CommentRepository
): ResourceValidator {
    override fun getCurrentUserId(): Long {
        return authenticationFacade.getPrincipal().userId
    }

    override fun validateMember(id: Long, type: Char) {
        val memberId = getCurrentUserId()
        when(type) {
            'M' -> if(!memoryRepository.existsByIdAndMemberId(id, memberId))
                throw MemberException(MemberExceptionType.NOT_AUTHORIZED_MEMBER)
            'C' -> if(!commentRepository.existsByIdAndMemberId(id, memberId))
                throw MemberException(MemberExceptionType.NOT_AUTHORIZED_MEMBER)
        }
    }
}