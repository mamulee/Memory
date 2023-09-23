package com.baby.memory.common.responses.error

import com.baby.memory.common.responses.error.exception.BaseException

class ErrorResponse(
    val errorCode: String,
    val errorMessage: String
) {
    companion object{
        fun of(exception: BaseException) = ErrorResponse(
            errorCode = exception.baseExceptionType.errorCode,
            errorMessage = exception.baseExceptionType.errorMessage
                .ifEmpty { exception.message ?: "Unknown Error" }
        )
        fun of(errorCode: String, errorMessage: String) = ErrorResponse(
            errorCode = errorCode,
            errorMessage = errorMessage
        )
    }
}