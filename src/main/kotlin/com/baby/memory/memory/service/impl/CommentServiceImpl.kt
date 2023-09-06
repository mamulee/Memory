package com.baby.memory.memory.service.impl

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
    override fun createComment(req: CommentRequestDto) {
        val member = getMember(req.memberId)
        val memory = getMemory(req.memoryId)
        val comment = req.toEntity(member, memory)
        commentRepository.save(comment)
    }

    @Transactional
    override fun updateComment(req: CommentRequestDto) {
        val comment = getComment(req.commentId!!)
        comment.content = req.content
    }

    @Transactional(readOnly = true)
    override fun getComments(memoryId: Long): MutableList<CommentResponseDto> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = getComment(commentId)
        comment.isDeleted = true
    }

    private fun getMember(memberId: Long) = memberRepository.findByIdOrNull(memberId)
        ?: throw Exception("해당 사용자를 찾을 수 없습니다.")
    private fun getMemory(memoryId: Long) = memoryRepository.findByIdOrNull(memoryId)
        ?: throw Exception("해당 게시글을 찾을 수 없습니다.")
    private fun getComment(commentId: Long) = commentRepository.findByIdOrNull(commentId)
        ?: throw Exception("해당 댓글을 찾을 수 없습니다.")
}