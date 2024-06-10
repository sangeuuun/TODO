package org.teamsparta.todo.domain.todo.dto

import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.todo.model.Todo
import org.teamsparta.todo.domain.user.model.toResponse
import java.time.LocalDateTime

data class TodosResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val authorName: String,
    val status: Boolean,
    val comments: List<CommentResponse>,
) {
    companion object {
        fun from(todo: Todo): TodosResponse {
            return TodosResponse(
                todo.id ?: throw IllegalStateException("Todo ID cannot be null"),
                todo.title,
                todo.content,
                todo.date,
                todo.user.toResponse().nickname,
                todo.status,
                todo.comments.map { CommentResponse.from(it) },
            )
        }
    }
}