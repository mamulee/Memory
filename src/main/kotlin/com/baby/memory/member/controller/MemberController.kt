package com.baby.memory.member.controller

import com.baby.memory.common.dto.CustomUser
import com.baby.memory.common.responses.success.MemberSuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/new")
    fun signUp(@RequestBody req: MemberRequestDto): ResponseEntity<SuccessResponse> {
        memberService.signUp(req)
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.SIGN_UP
        )
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody req: MemberRequestDto): ResponseEntity<SuccessResponse> {
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.SIGN_IN,
            memberService.signIn(req)
        )
    }

    @GetMapping("/me")
    fun getMyInfo(): ResponseEntity<SuccessResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.GET_MY_INFO,
            memberService.getMyInfo(userId)
        )
    }

    // TODO : 정보 조회 닉넴 비번 분리
    @PatchMapping("/me")
    fun updateMyInfo(@RequestBody req: MemberUpdateRequestDto): ResponseEntity<SuccessResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.UPDATE_MEMBER_NAME,
            memberService.updateMyInfo(userId, req)
        )
    }

    @PostMapping("/following/{followedId}")
    fun addFollowing(
        @PathVariable followedId: Long
    ):ResponseEntity<SuccessResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberService.addFollowing(userId, followedId)
        return SuccessResponse.toResponseEntity(MemberSuccessType.FOLLOWING)
    }
}