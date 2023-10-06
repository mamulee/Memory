package com.baby.memory.member.repository

import com.baby.memory.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>, CustomMemberRepository {
    fun existsByMemberEmail(memberEmail: String): Boolean
    fun existsByMemberName(memberName: String): Boolean
    fun findByMemberEmail(memberEmail: String): Member?
    fun findByMemberName(memberName: String): Member?
    fun existsByIdAndFollowingsId(memberId: Long, followerId: Long): Boolean
}