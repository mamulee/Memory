package com.baby.memory.member.controller

import com.baby.memory.common.authority.TokenInfo
import com.baby.memory.common.dto.CustomUser
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.dto.response.MemberResponseDto
import com.baby.memory.member.service.MemberService
import org.springframework.security.core.context.SecurityContextHolder
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

    @PostMapping("/sign-in")
    fun signIn(@RequestBody req: MemberRequestDto): TokenInfo {
        return memberService.signIn(req)
    }

    @GetMapping("/me")
    fun getMyInfo(): MemberResponseDto {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        return memberService.getMyInfo(userId)
    }

    @PatchMapping("/me")
    fun updateMyInfo(@RequestBody req: MemberUpdateRequestDto) {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberService.updateMyInfo(userId, req)
    }

    @PostMapping("/following/{followedId}")
    fun addFollowing(
        @PathVariable followedId: Long
    ) {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberService.addFollowing(userId, followedId)
    }
}