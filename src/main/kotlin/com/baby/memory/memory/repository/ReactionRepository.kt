package com.baby.memory.memory.repository

import com.baby.memory.memory.entity.Reaction
import org.springframework.data.jpa.repository.JpaRepository

interface ReactionRepository : JpaRepository<Reaction, Long> {
    fun findAllByMemoryId(memoryId: Long): List<Reaction>?
    fun findByMemberIdAndMemoryId(memberId: Long, memoryId: Long): Reaction?
}
