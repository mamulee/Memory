package com.baby.memory.memory.repository.impl

import com.baby.memory.memory.dto.request.MemorySearchRequestDto
import com.baby.memory.memory.entity.Memory
import com.baby.memory.memory.entity.QMemory.memory
import com.baby.memory.memory.repository.CustomMemoryRepository
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable

class CustomMemoryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CustomMemoryRepository {
    override fun getMemoriesWithSearch(pageable: Pageable, req: MemorySearchRequestDto): List<Memory> {
        val expressions: Array<BooleanExpression?> = transferExpressions(req)
        return jpaQueryFactory
            .selectFrom(memory)
            .where(
                *expressions,
                memory.isDeleted.isFalse
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(memory.createdAt.desc())
            .fetch()
    }

    private fun searchContent(
        keyword: String?
    ): BooleanExpression? {
        return keyword?.takeIf { it.isNotEmpty() }?.let { memory.content.contains(it) }
    }

    private fun transferExpressions(
        req: MemorySearchRequestDto
    ): Array<BooleanExpression?> {
        return arrayOf(
            searchContent(req.keyword)
        )
    }
}
