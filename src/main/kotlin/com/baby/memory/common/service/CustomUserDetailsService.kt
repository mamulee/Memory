package com.baby.memory.common.service

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.member.entity.Member
import com.baby.memory.member.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        memberRepository.findByMemberEmail(username)
            ?.let { createUserDetails(it) } ?: throw UsernameNotFoundException("사용자 정보 없음.")

    private fun createUserDetails(member: Member): UserDetails =
        CustomUser(
            member.id,
            member.memberEmail,
            member.memberPassword,
            listOf(SimpleGrantedAuthority("ROLE_${member.memberRole}"))
        )
}
