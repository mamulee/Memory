package com.baby.memory.memory.repository

import com.baby.memory.member.entity.Member
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.entity.Memory
import com.baby.memory.memory.entity.SavedMemory
import org.springframework.data.domain.Pageable

interface CustomMemoryRepository {
    fun getMemoriesWithSearch(pageable: Pageable, req: MemorySearchRequestDto): List<Memory>
    fun findSavedMemoriesByMemberId(member: Member, pageable: Pageable): List<SavedMemory>
}
