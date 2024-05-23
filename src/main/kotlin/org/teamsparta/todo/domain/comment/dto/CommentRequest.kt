package org.teamsparta.todo.domain.comment.dto

data class CommentRequest(
    val nickname: String,
    val password: String,
    val content: String
)
