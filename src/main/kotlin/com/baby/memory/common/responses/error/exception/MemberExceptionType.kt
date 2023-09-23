package com.baby.memory.common.responses.error.exception

import org.springframework.http.HttpStatus

enum class MemberExceptionType(
    override val errorCode: String,
    override val httpStatus: HttpStatus,
    override val errorMessage: String
): BaseExceptionType {
    DUPLICATED_MEMBER_EMAIL(
        "ME001", HttpStatus.CONFLICT, "이미 존재하는 회원 이메일입니다."
    ),
    DUPLICATED_MEMBER_NAME(
        "ME002", HttpStatus.CONFLICT, "이미 존재하는 회원 이름입니다."
    ),
    NOT_FOUND_MEMBER(
        "ME003", HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."
    ),
    INCORRECT_PASSWORD(
        "ME004", HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."
    )
}