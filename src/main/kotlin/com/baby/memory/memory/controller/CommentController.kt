package com.baby.memory.memory.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.CommentSuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memories/{memoryId}/comments")
class CommentController(
    private val resourceValidator: ResourceValidator,
    private val commentService: CommentService
) {

    @PostMapping("/new")
    fun createComment(
        @PathVariable memoryId: Long,
        @RequestBody req: CommentRequestDto
    ): ResponseEntity<SuccessResponse> {
        val memberId = resourceValidator.getCurrentUserId()
        commentService.createComment(memberId, memoryId, req)
        return SuccessResponse.toResponseEntity(
            CommentSuccessType.CREATE_COMMENT
        )
    }

    @PatchMapping("{commentId}")
    fun updateComment(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long,
        @RequestBody req: CommentRequestDto
    ): ResponseEntity<SuccessResponse> {
        // 수정 성공 시 뭐 보내야할지 상의 필요
        resourceValidator.validateMember(commentId, 'C')
        commentService.updateComment(memoryId, commentId, req)
        return SuccessResponse.toResponseEntity(
            CommentSuccessType.UPDATE_COMMENT
        )
    }

    @DeleteMapping("{commentId}")
    fun deleteMemory(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<SuccessResponse> {
        resourceValidator.validateMember(commentId, 'C')
        commentService.deleteComment(memoryId, commentId)
        return SuccessResponse.toResponseEntity(
            CommentSuccessType.DELETE_COMMENT
        )
    }
}
