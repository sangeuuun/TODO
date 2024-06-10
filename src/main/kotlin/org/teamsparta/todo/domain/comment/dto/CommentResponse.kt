package org.teamsparta.todo.domain.comment.dto

import org.teamsparta.todo.domain.comment.model.Comment
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.user.dto.UserResponse
import org.teamsparta.todo.domain.user.model.toResponse

data class CommentResponse(
    val id: Long,
    val content: String,
    val authName: String,
    val todoId: Long
) {
    companion object {
        fun from(comment: Comment): CommentResponse {
            return CommentResponse(
                comment.id ?: throw IllegalStateException("Comment Id cannot be null"),
                comment.content,
                comment.user.toResponse().nickname,
                comment.todo.id ?: throw IllegalStateException("postId cannot be null")
            )
        }
    }
}
