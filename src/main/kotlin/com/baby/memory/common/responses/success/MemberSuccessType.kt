package com.baby.memory.common.responses.success

import org.springframework.http.HttpStatus

enum class MemberSuccessType(
    override val successCode: String,
    override val httpStatus: HttpStatus,
    override val successMessage: String
) : BaseSuccessType {
    SIGN_UP(
        "MS001",
        HttpStatus.CREATED,
        "Memory 회원이 되신 것을 축하합니다!"
    ),
    SIGN_IN(
        "MS002",
        HttpStatus.OK,
        "로그인 성공!"
    ),
    SIGN_OUT(
        "MS003",
        HttpStatus.OK,
        "로그아웃 성공!"
    ),
    UPDATE_MEMBER_NAME(
        "MS004",
        HttpStatus.OK,
        "닉네임 변경 성공!"
    ),
    UPDATE_MEMBER_PASSWORD(
        "MS005",
        HttpStatus.OK,
        "비밀번호 변경 성공!"
    ),
    GET_MY_INFO(
        "MS006",
        HttpStatus.OK,
        "내 회원 정보 조회 성공!"
    ),
    FOLLOWING(
        "MS007",
        HttpStatus.OK,
        "팔로우 성공!"
    ),
    UNFOLLOW(
        "MS008",
        HttpStatus.OK,
        "팔로우 취소 성공!"
    ),
    GET_MEMBERS(
        "MS009",
        HttpStatus.OK,
        "전체 회원 조회 성공!"
    ),
    GET_MEMBER_INFO(
        "MS010",
        HttpStatus.OK,
        "회원 조회 성공!"
    )
}
