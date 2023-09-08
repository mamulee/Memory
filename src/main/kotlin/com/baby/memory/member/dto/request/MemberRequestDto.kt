package com.baby.memory.member.dto.request

import com.baby.memory.common.status.ROLE
import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.Memory
import jakarta.validation.constraints.NotBlank

data class MemberRequestDto(
    @NotBlank(message = "회원 이메일은 공백일 수 없습니다.")
    val memberEmail: String,
    @NotBlank(message = "회원 비밀번호는 공백일 수 없습니다.")
    val memberPassword: String,
    val memberName: String?
) {
    fun toEntity(name: String): Member = Member(
        memberEmail,
        memberPassword,
        memberName ?: name
    )
}
