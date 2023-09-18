package com.baby.memory.memory.controller

import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.request.ReactionRequestDto
import com.baby.memory.memory.service.CommentService
import com.baby.memory.memory.service.ReactionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memories/{memoryId}/reactions")
class ReactionController(
    private val reactionService: ReactionService
) {

    @PostMapping("/new")
    fun reaction(
        @PathVariable memoryId: Long,
        @RequestBody req: ReactionRequestDto) {
        reactionService.reaction(memoryId, req)
    }
}