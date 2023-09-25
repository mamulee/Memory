package com.baby.memory.common.responses.success

import org.springframework.http.HttpStatus

enum class MemorySuccessType(
    override val successCode: String,
    override val httpStatus: HttpStatus,
    override val successMessage: String
): BaseSuccessType {
    CREATE_MEMORY(
        "BS001", HttpStatus.CREATED, "게시글 등록 성공!"
    ),
    UPDATE_MEMORY(
        "BS002", HttpStatus.OK, "게시글 수정 성공!"
    ),
    DELETE_MEMORY(
        "BS003", HttpStatus.OK, "게시글 삭제 성공!"
    ),
    GET_MEMORY(
        "BS004", HttpStatus.OK, "전체 게시글 조회 성공!"
    ),
    GET_MY_MEMORY(
        "BS005", HttpStatus.OK, "내 게시글 조회 성공!"
    ),
    GET_SAVED_MEMORY(
        "BS006", HttpStatus.OK, "저장된 게시글 조회 성공!"
    ),
    SAVE_MEMORY(
        "BS007", HttpStatus.OK, "게시글 저장 성공!"
    ),
    UNSAVE_MEMORY(
        "BS008", HttpStatus.OK, "게시글 저장 삭제 성공!"
    )
}