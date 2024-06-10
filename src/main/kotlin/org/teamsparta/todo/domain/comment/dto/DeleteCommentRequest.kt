package org.teamsparta.todo.domain.comment.dto

import jakarta.validation.constraints.NotBlank

data class DeleteCommentRequest(
    @field:NotBlank
    val password: String
)
