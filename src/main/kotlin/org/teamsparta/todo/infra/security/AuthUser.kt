package org.teamsparta.todo.infra.security

data class AuthUser(
    val id: Long,
    val token: String
)
