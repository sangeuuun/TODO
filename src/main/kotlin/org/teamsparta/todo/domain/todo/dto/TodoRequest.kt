package org.teamsparta.todo.domain.todo.dto

data class TodoRequest(
    val title: String,
    val content: String,
    val name: String
)
