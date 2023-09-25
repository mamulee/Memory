package com.baby.memory.memory.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.service.CommentService
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
        @RequestBody req: CommentRequestDto) {
        val memberId = resourceValidator.getCurrentUserId()
        commentService.createComment(memberId, memoryId, req)
    }

    @PatchMapping("{commentId}")
    fun updateComment(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long,
        @RequestBody req: CommentRequestDto
    ) {
        // 수정 성공 시 뭐 보내야할지 상의 필요
        resourceValidator.validateMember(commentId, 'C')
        commentService.updateComment(memoryId, commentId, req)
    }
    @DeleteMapping("{commentId}")
    fun deleteMemory(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long
    ) {
        resourceValidator.validateMember(commentId, 'C')
        commentService.deleteComment(memoryId, commentId)
    }
}