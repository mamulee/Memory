package com.baby.memory.memory.repository

import com.baby.memory.memory.entity.Memory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemoryRepository: JpaRepository<Memory, Long> {
//    @EntityGraph(attributePaths = ["comment"])
    fun findAllByMemberId(memberId: Long, pageable: Pageable) : Page<Memory>?
    fun existsByIdAndMemberId(memoryId: Long, memberId: Long): Boolean
}