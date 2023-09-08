package com.baby.memory.member.service.impl

import com.baby.memory.common.authority.JwtTokenProvider
import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.common.status.ROLE
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.member.service.MemberService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
): MemberService {
    @Transactional
    override fun signUp(req: MemberRequestDto) {
        validateMemberEmail(req.memberEmail)
        val randomStr = "user_${UUID.randomUUID()}"
        val memberName = randomStr.substring(0, randomStr.indexOf("-"))
        validateMemberName(memberName)
        val member = req.toEntity(memberName)
        member.memberRole = ROLE.MEMBER
        memberRepository.save(member)
    }

    override fun signIn(req: MemberRequestDto): TokenInfo {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(req.memberEmail, req.memberPassword)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
//        val member = memberRepository.findByMemberEmail(req.memberEmail) ?: throw Exception ("가입되지 않은 이메일입니다.")
//        if(member.memberPassword != req.memberPassword)
//            throw Exception("비밀번호가 일치하지 않습니다.")
        return jwtTokenProvider.createToken(authentication)
    }

    private fun validateMemberEmail(memberEmail: String) {
        if(memberRepository.existsByMemberEmail(memberEmail))
            throw Exception("이미 사용 중인 이메일입니다.")
    }
    private fun validateMemberName(memberName: String) {
        if(memberRepository.existsByMemberName(memberName))
            throw Exception("이미 사용 중인 이름입니다.")
    }
}