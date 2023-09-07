package com.baby.memory.member.service.impl

import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.repository.MemberRepository
import com.baby.memory.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    @Transactional
    override fun signUp(req: MemberRequestDto) {
        validateMemberEmail(req.memberEmail)
        val randomStr = "user_${UUID.randomUUID()}"
        val memberName = randomStr.substring(0, randomStr.indexOf("-"))
        validateMemberName(memberName)
        val member = req.toEntity(memberName)
        memberRepository.save(member)
    }

    override fun signIn(req: MemberRequestDto) {
        TODO("Not yet implemented")
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