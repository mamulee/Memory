package com.baby.memory.member.dto.request

import com.baby.memory.member.entity.Member
import jakarta.validation.constraints.NotBlank
import org.springframework.security.crypto.password.PasswordEncoder

data class MemberRequestDto(
    @NotBlank(message = "회원 이메일은 공백일 수 없습니다.")
    val memberEmail: String,
    @NotBlank(message = "회원 비밀번호는 공백일 수 없습니다.")
    val memberPassword: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder): Member = Member(
        memberEmail,
        memberPassword = passwordEncoder.encode(memberPassword)
    )
}
