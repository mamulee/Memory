package com.baby.memory.common.responses.success

import org.springframework.http.HttpStatus

interface BaseSuccessType{
    val successCode: String
    val httpStatus: HttpStatus
    val successMessage: String
}