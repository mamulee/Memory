package com.baby.memory.common.responses.error.exception

import org.springframework.http.HttpStatus

enum class CommentExceptionType(
    override val errorCode: String,
    override val httpStatus: HttpStatus,
    override val errorMessage: String
): BaseExceptionType {
    NOT_FOUND_COMMENT(
        "CE001", HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."
    )
}