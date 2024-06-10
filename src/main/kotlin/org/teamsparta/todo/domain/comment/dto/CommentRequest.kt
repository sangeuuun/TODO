package org.teamsparta.todo.domain.comment.dto

import jakarta.validation.constraints.NotBlank

data class CommentRequest(
    @field:NotBlank
    val password: String,

    @field:NotBlank
    val content: String
)
