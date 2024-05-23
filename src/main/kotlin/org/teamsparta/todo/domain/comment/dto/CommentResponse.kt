package org.teamsparta.todo.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val nickname: String,
    val content: String,
    val todoId: Long
)
