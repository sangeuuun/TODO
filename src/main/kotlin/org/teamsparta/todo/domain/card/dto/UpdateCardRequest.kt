package org.teamsparta.todo.domain.card.dto

data class UpdateCardRequest(
    val title: String,
    val content: String,
    val name: String
)
