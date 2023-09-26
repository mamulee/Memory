package com.baby.memory.member.repository.impl

import com.baby.memory.member.entity.Member
import com.baby.memory.member.repository.CustomMemberRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class CustomMemberRepositoryImpl(
    val jpaQueryFactory: JPAQueryFactory
): CustomMemberRepository {
    override fun getMembersWithFollowStatus(pageable: Pageable): Page<Member> {
        TODO("Not yet implemented")
    }

}