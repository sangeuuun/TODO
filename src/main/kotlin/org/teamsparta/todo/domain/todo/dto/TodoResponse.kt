package org.teamsparta.todo.domain.todo.dto

import org.teamsparta.todo.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val name: String,
    val status: Boolean,
    val comments: List<CommentResponse>,
)
