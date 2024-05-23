package org.teamsparta.todo.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.teamsparta.todo.domain.comment.dto.CommentRequest
import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.comment.dto.DeleteCommentRequest
import org.teamsparta.todo.domain.comment.service.CommentService

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun writeComment(
        @PathVariable todoId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.writeComment(todoId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: DeleteCommentRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(todoId, commentId, request))
    }

}