package com.baby.memory.member.repository

import com.baby.memory.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface CustomMemberRepository {
    fun getMembersWithFollowStatus(pageable: Pageable): Page<Member>
}