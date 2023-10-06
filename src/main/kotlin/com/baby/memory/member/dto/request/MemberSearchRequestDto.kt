package com.baby.memory.member.dto.request

data class MemberSearchRequestDto(
    var memberId: Long?,
    val keyword: String?
)