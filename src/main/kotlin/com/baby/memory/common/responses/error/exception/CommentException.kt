package com.baby.memory.common.responses.error.exception

class CommentException(
    private val exceptionType: BaseExceptionType
): BaseException(exceptionType)