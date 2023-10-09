package com.baby.memory.member.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.MemberSuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.member.dto.request.MemberRequestDto
import com.baby.memory.member.dto.request.MemberSearchRequestDto
import com.baby.memory.member.dto.request.MemberUpdateRequestDto
import com.baby.memory.member.service.MemberService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
    private val resourceValidator: ResourceValidator,
    private val memberService: MemberService
) {
    @PostMapping("")
    fun getMembers(
        pageable: Pageable,
        @RequestBody req: MemberSearchRequestDto
    ): ResponseEntity<SuccessResponse> {
        val selfId = resourceValidator.getCurrentUserId()
        req.memberId = selfId
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.GET_MEMBERS,
            memberService.getMembers(req, pageable)
        )
    }
    @GetMapping("{memberId}")
    fun getMember(
        @PathVariable memberId: Long,
    ): ResponseEntity<SuccessResponse> {
        val selfId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.GET_MEMBER_INFO,
            memberService.getMemberInfo(memberId, selfId)
        )
    }
    @GetMapping("/member/{memberName}")
    fun getMemberByMemberName(
        @PathVariable memberName: String,
    ): ResponseEntity<SuccessResponse> {
        val selfId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.GET_MEMBER_INFO,
            memberService.getMemberInfoByMemberName(memberName, selfId)
        )
    }

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
        val memberId = resourceValidator.getCurrentUserId()
        return SuccessResponse.toResponseEntity(
            MemberSuccessType.GET_MY_INFO,
            memberService.getMyInfo(memberId)
        )
    }

    @PatchMapping("/me")
    fun updateMyInfo(@RequestBody req: MemberUpdateRequestDto): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        val success = req.memberName?.let { MemberSuccessType.UPDATE_MEMBER_NAME }
            ?: MemberSuccessType.UPDATE_MEMBER_PASSWORD
        return SuccessResponse.toResponseEntity(
            success,
            memberService.updateMyInfo(memberId, req)
        )
    }

    @GetMapping("/following/{followedId}")
    fun toggleFollowing(
        @PathVariable followedId: Long
    ):ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        memberService.toggleFollowing(memberId, followedId)
        // TODO : 팔로우 / 팔로우 취소 구분?
        return SuccessResponse.toResponseEntity(MemberSuccessType.FOLLOWING)
    }
}