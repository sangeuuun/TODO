package org.teamsparta.todo.domain.user.dto

data class UserResponse(
    val id: Long,
    val nickname: String,
    val email: String,
)
