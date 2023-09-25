package com.baby.memory.memory.service.impl

import com.baby.memory.common.responses.error.exception.*
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.response.CommentResponseDto
import com.baby.memory.memory.repository.CommentRepository
import com.baby.memory.memory.repository.MemoryRepository
import com.baby.memory.memory.service.CommentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val memberRepository: MemberRepository,
    private val memoryRepository: MemoryRepository,
    private val commentRepository: CommentRepository
): CommentService {
    @Transactional
    override fun createComment(memberId: Long, memoryId: Long, req: CommentRequestDto) {
        val member = getMember(memberId)
        val memory = getMemory(memoryId)
        val comment = req.toEntity(member, memory)
        commentRepository.save(comment)
    }

    @Transactional
    override fun updateComment(memoryId: Long, commentId: Long, req: CommentRequestDto) {
        val memory = getMemory(memoryId)
        val comment = memory.comments.firstOrNull { it.id == commentId } ?: throw Exception("해당 댓글을 찾을 수 없습니다.")
        comment.content = req.content
    }

    @Transactional(readOnly = true)
    override fun getComments(memoryId: Long): List<CommentResponseDto> {
        getMemory(memoryId)
        return commentRepository.findAllByMemoryId(memoryId)!!.map { CommentResponseDto.of(it) }
    }

    @Transactional
    override fun deleteComment(memoryId: Long, commentId: Long) {
        val memory = getMemory(memoryId)
        val comment = memory.comments.firstOrNull { it.id == commentId } ?: throw Exception("해당 댓글을 찾을 수 없습니다.")
        comment.isDeleted = true
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw MemoryException(MemoryExceptionType.NOT_FOUND_MEMORY)
    private fun getComment(commentId: Long) = commentRepository.findByIdOrNull(commentId)
        ?: throw CommentException(CommentExceptionType.NOT_FOUND_COMMENT)
}