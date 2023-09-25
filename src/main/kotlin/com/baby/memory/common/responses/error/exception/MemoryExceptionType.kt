package com.baby.memory.common.responses.error.exception

import org.springframework.http.HttpStatus

enum class MemoryExceptionType(
    override val errorCode: String,
    override val httpStatus: HttpStatus,
    override val errorMessage: String
): BaseExceptionType {
    NOT_FOUND_MEMORY(
        "BE001", HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."
    )
}