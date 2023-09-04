package com.baby.memory.memory.repository

import com.baby.memory.memory.entity.Memory
import org.springframework.data.jpa.repository.JpaRepository

interface MemoryRepository: JpaRepository<Memory, Long> {
}