package com.baby.memory.member.repository

import com.baby.memory.member.dto.request.MemberSearchRequestDto
import com.baby.memory.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomMemberRepository {
    fun getMembersWithFollowStatus(pageable: Pageable): Page<Member>
    fun getMembersWithSearch(pageable: Pageable, req: MemberSearchRequestDto): List<Member>
}
