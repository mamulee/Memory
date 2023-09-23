package com.baby.memory.common.responses.success

import com.baby.memory.common.responses.error.exception.BaseException
import org.springframework.http.ResponseEntity

class SuccessResponse(
    val successCode: String,
    val successMessage: String,
    val responseObject: Any? = null
) {
    companion object{
        fun toResponseEntity(successType: BaseSuccessType) = ResponseEntity
            .status(successType.httpStatus)
            .body(SuccessResponse(
                successType.successCode,
                successType.successMessage
                ))

        fun toResponseEntity(successType: BaseSuccessType, dto: Any) = ResponseEntity
            .status(successType.httpStatus)
            .body(SuccessResponse(
                successType.successCode,
                successType.successMessage,
                dto
            ))

    }
}