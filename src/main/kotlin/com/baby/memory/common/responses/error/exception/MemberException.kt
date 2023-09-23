package com.baby.memory.common.responses.error.exception

class MemberException(
    private val exceptionType: BaseExceptionType
): BaseException(exceptionType)