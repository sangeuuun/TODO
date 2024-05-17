package org.teamsparta.todo.domain.card.dto

import java.time.LocalDateTime

data class CardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val name: String,
    val status: Boolean
)
