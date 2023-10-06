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
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
): MemberService {
    @Transactional
    override fun signUp(req: MemberRequestDto) {
        validateMemberEmail(req.memberEmail)
        val randomStr = "user_${UUID.randomUUID()}"
        val memberName = randomStr.substring(0, randomStr.indexOf("-"))
        validateMemberName(memberName)
        val member = req.toEntity(passwordEncoder)
        member.memberName = memberName
        member.memberRole = ROLE.MEMBER
        memberRepository.save(member)
    }

    override fun signIn(req: MemberRequestDto): TokenInfo {
        val member = memberRepository.findByMemberEmail(req.memberEmail)
            ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
        if(!passwordEncoder.matches(req.memberPassword, member.memberPassword))
            throw MemberException(MemberExceptionType.INCORRECT_PASSWORD)
        val authenticationToken =
            UsernamePasswordAuthenticationToken(req.memberEmail, req.memberPassword)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }

    // TODO : 최적화 필요
    @Transactional(readOnly = true)
    override fun getMembers(memberId: Long, pageable: Pageable): Page<MemberResponseDto> {
        val self = getMember(memberId)
        return memberRepository.findAll(pageable)
            .map { MemberResponseDto.of(it, it.followers.contains(self)) }
    }

    @Transactional(readOnly = true)
    override fun getMyInfo(memberId: Long): MemberResponseDto {
        val member = getMember(memberId)
        return MemberResponseDto.of(member, false)
    }

    @Transactional(readOnly = true)
    override fun getMemberInfo(memberId: Long, selfId: Long): MemberResponseDto {
        val member = getMember(memberId)
        val self = getMember(selfId)
        return MemberResponseDto.of(member, member.followers.contains(self))
    }

    @Transactional(readOnly = true)
    override fun getMemberInfoByMemberName(memberName: String, selfId: Long): MemberResponseDto {
        val member = getMemberByMemberName(memberName)
        val self = getMember(selfId)
        return MemberResponseDto.of(member, member.followers.contains(self))
    }

    @Transactional
    override fun updateMyInfo(memberId: Long, req: MemberUpdateRequestDto) {
        val member = getMember(memberId)
        // 비밀번호 변경
        req.memberPassword?.let { newPassword ->
            member.memberPassword = passwordEncoder.encode(newPassword)
        }
        // 회원 닉네임 변경
        req.memberName?.let { newMemberName ->
            validateMemberName(newMemberName)
            member.memberName = newMemberName
        }
    }

    @Transactional
    override fun toggleFollowing(memberId: Long, followedId: Long) {
        val followed = getMember(followedId)
        val member = getMember(memberId)
        if(followed == member) throw MemberException(MemberExceptionType.NOT_AUTHORIZED_MEMBER)
        if (member.followings.remove(followed)) {
            // 이미 팔로우 중인 경우 팔로우 취소
            followed.followers.remove(member)
        } else {
            // 팔로우하지 않은 경우 팔로우
            member.followings.add(followed)
            followed.followers.add(member)
        }
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

    private fun getMemberByMemberName(memberName: String): Member {
        return memberRepository.findByMemberName(memberName)
            ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
    }
    private fun isFollowing(memberId: Long, followerId: Long): Boolean {
        return memberRepository.existsByIdAndFollowingsId(memberId, followerId)
    }
}