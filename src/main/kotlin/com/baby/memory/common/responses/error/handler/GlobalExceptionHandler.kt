package com.baby.memory.common.responses.error.handler

import com.baby.memory.common.responses.error.ErrorResponse
import com.baby.memory.common.responses.error.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<ErrorResponse?> {
//        TODO: logger.error("handleBaseException", e)
        val responseBody = ErrorResponse.of(exception)
        return ResponseEntity(responseBody, exception.baseExceptionType.httpStatus)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse?> {
//        TODO : logger.error("handleException", exception)
        val responseBody = ErrorResponse.of("IE001", exception.message!!)
        return ResponseEntity(responseBody, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
