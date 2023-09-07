package com.baby.memory.member.repository

import com.baby.memory.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun existsByMemberEmail(memberEmail: String): Boolean
    fun existsByMemberName(memberName: String): Boolean
}