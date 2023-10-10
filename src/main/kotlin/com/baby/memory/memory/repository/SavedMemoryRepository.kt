package com.baby.memory.memory.repository

import com.baby.memory.memory.entity.SavedMemory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SavedMemoryRepository : JpaRepository<SavedMemory, Long> {
//    @EntityGraph(attributePaths = ["comment"])
    fun findAllByMemberId(memberId: Long, pageable: Pageable): Page<SavedMemory>?
    fun findByMemberIdAndMemoryId(memberId: Long, memoryId: Long): SavedMemory?
}
