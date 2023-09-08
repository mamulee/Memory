package com.baby.memory.member.controller

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/new")
    fun signUp(@RequestBody req: MemberRequestDto){
        memberService.signUp(req)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody req: MemberRequestDto): TokenInfo {
        return memberService.signIn(req)
    }
}