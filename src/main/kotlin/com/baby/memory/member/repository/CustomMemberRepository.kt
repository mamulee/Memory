package com.baby.memory.member.repository

import com.baby.memory.member.dto.request.MemberSearchRequestDto
import com.baby.memory.member.entity.Member
import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.entity.Memory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface CustomMemberRepository {
    fun getMembersWithFollowStatus(pageable: Pageable): Page<Member>
    fun getMembersWithSearch(pageable: Pageable, req: MemberSearchRequestDto): List<Member>

}