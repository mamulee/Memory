package com.baby.memory.common.responses.success

import org.springframework.http.HttpStatus

enum class ReactionSuccessType(
    override val successCode: String,
    override val httpStatus: HttpStatus,
    override val successMessage: String
): BaseSuccessType {
    REACTION(
        "RS001", HttpStatus.CREATED, "게시글 감정 표현 성공!"
    )
}