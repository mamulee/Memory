package com.baby.memory.member.service.impl

import com.baby.memory.common.authority.JwtTokenProvider
import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.common.responses.error.exception.MemberException
import com.baby.memory.common.responses.error.exception.MemberExceptionType
import com.baby.memory.common.status.ROLE
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto
import com.baby.memory.member.entity.Member
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.member.service.MemberService
import org.springframework.data.repository.findByIdOrNull
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
        val member = req.toEntity()
        member.memberName = memberName
        member.memberRole = ROLE.MEMBER
        memberRepository.save(member)
    }

    override fun signIn(req: MemberRequestDto): TokenInfo {
        val member = memberRepository.findByMemberEmail(req.memberEmail)
            ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
        if(member.memberPassword != req.memberPassword)
            throw MemberException(MemberExceptionType.INCORRECT_PASSWORD)
        val authenticationToken =
            UsernamePasswordAuthenticationToken(req.memberEmail, req.memberPassword)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }

    @Transactional(readOnly = true)
    override fun getMyInfo(memberId: Long): MemberResponseDto {
        val member = getMember(memberId)
        return MemberResponseDto.of(member)
    }

    @Transactional
    override fun updateMyInfo(memberId: Long, req: MemberUpdateRequestDto) {
        val member = getMember(memberId)
        validateMemberName(req.memberName!!)
        member.memberName = req.memberName
    }

    @Transactional
    override fun addFollowing(memberId: Long, followedId: Long) {
        val followed = getMember(followedId)
        val member = getMember(memberId)
        member.addFollowing(followed)
    }

    private fun validateMemberEmail(memberEmail: String) {
        if(memberRepository.existsByMemberEmail(memberEmail))
            throw MemberException(MemberExceptionType.DUPLICATED_MEMBER_EMAIL)
    }
    private fun validateMemberName(memberName: String) {
        if(memberRepository.existsByMemberName(memberName))
            throw MemberException(MemberExceptionType.DUPLICATED_MEMBER_NAME)
    }
    private fun getMember(memberId: Long): Member {
        return memberRepository.findByIdOrNull(memberId)
            ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    }
}