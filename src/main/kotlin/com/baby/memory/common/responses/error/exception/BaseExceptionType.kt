package com.baby.memory.common.responses.error.exception

import org.springframework.http.HttpStatus

interface BaseExceptionType {
    val errorCode: String
    val httpStatus: HttpStatus
    val errorMessage: String
}
