package com.baby.memory.common.responses.success

import org.springframework.http.HttpStatus

enum class CommentSuccessType(
    override val successCode: String,
    override val httpStatus: HttpStatus,
    override val successMessage: String
) : BaseSuccessType {
    CREATE_COMMENT(
        "BS001",
        HttpStatus.CREATED,
        "댓글 등록 성공!"
    ),
    UPDATE_COMMENT(
        "BS002",
        HttpStatus.OK,
        "댓글 수정 성공!"
    ),
    DELETE_COMMENT(
        "BS003",
        HttpStatus.OK,
        "댓글 삭제 성공!"
    ),
    GET_COMMENT(
        "BS004",
        HttpStatus.OK,
        "전체 댓글 조회 성공!"
    )
}
