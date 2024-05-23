package org.teamsparta.todo.domain.todo.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val name: String,
    val status: Boolean
)
