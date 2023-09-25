package com.baby.memory.memory.repository

import com.baby.memory.memory.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findAllByMemoryId(memoryId: Long) : List<Comment>?
    fun existsByIdAndMemberId(commentId: Long, memberId: Long): Boolean
}