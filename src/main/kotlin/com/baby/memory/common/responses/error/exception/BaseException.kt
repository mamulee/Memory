package com.baby.memory.common.responses.error.exception

abstract class BaseException(
    val baseExceptionType: BaseExceptionType
) : RuntimeException()
