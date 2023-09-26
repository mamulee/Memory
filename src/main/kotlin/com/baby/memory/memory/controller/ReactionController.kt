package com.baby.memory.memory.controller

import com.baby.memory.common.helper.ResourceValidator
import com.baby.memory.common.responses.success.ReactionSuccessType
import com.baby.memory.common.responses.success.SuccessResponse
import com.baby.memory.memory.dto.request.CommentRequestDto
import com.baby.memory.memory.dto.request.ReactionRequestDto
import com.baby.memory.memory.service.CommentService
import com.baby.memory.memory.service.ReactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memories/{memoryId}/reactions")
class ReactionController(
    private val resourceValidator: ResourceValidator,
    private val reactionService: ReactionService
) {

    @PostMapping("/new")
    fun reaction(
        @PathVariable memoryId: Long,
        @RequestBody req: ReactionRequestDto): ResponseEntity<SuccessResponse> {
        //TODO : 어떤 거 반환..?
        val memberId = resourceValidator.getCurrentUserId()
        reactionService.reaction(memoryId, memberId, req)
        return SuccessResponse.toResponseEntity(
            ReactionSuccessType.REACTION
        )
    }
}