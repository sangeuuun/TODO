package org.teamsparta.todo.domain.card.dto

data class CreateCardRequest(
    val title: String,
    val content: String,
    val name: String
)
