package com.baby.memory.common.responses.error.exception

class MemoryException(
    private val exceptionType: BaseExceptionType
) : BaseException(exceptionType)
