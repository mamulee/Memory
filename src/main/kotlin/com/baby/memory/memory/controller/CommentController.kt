package com.baby.memory.memory.controller

import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memories/{memoryId}/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping("/new")
    fun createComment(
        @PathVariable memoryId: Long,
        @RequestBody req: CommentRequestDto) {
        commentService.createComment(memoryId, req)
    }

    @PatchMapping("{commentId}")
    fun updateComment(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long,
        @RequestBody req: CommentRequestDto
    ) {
        // 수정 성공 시 뭐 보내야할지 상의 필요
        commentService.updateComment(memoryId, commentId, req)
    }
    @DeleteMapping("{commentId}")
    fun deleteMemory(
        @PathVariable memoryId: Long,
        @PathVariable commentId: Long
    ) {
        commentService.deleteComment(memoryId, commentId)
    }
}