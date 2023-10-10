package com.baby.memory.common.helper

interface ResourceValidator {
    fun getCurrentUserId(): Long
    fun validateMember(id: Long, type: Char)
}
