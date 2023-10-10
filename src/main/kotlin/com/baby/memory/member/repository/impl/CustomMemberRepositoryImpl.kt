package com.baby.memory.member.repository.impl

import com.baby.memory.member.dto.request.MemberSearchRequestDto
import com.baby.memory.member.entity.Member
import com.baby.memory.member.entity.QMember.member
import com.baby.memory.member.repository.CustomMemberRepository
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomMemberRepositoryImpl(
    val jpaQueryFactory: JPAQueryFactory
) : CustomMemberRepository {
    override fun getMembersWithFollowStatus(pageable: Pageable): Page<Member> {
        TODO("Not yet implemented")
    }

    override fun getMembersWithSearch(pageable: Pageable, req: MemberSearchRequestDto): List<Member> {
        return jpaQueryFactory
            .selectFrom(member)
            .where(searchMemberName(req.keyword))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(member.createdAt.desc())
            .fetch()
    }

    private fun searchMemberName(
        keyword: String?
    ): BooleanExpression? {
        return keyword?.takeIf { it.isNotEmpty() }?.let { member.memberName.contains(it) }
    }
}
