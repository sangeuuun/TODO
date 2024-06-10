package org.teamsparta.todo.domain.user.dto

import org.teamsparta.todo.domain.user.model.User
import org.teamsparta.todo.domain.user.model.toResponse

data class LoginResponse(
    val user: UserResponse,
    val accessToken: String
) {
    companion object {
        fun from(user: User, accessToken: String) =
            LoginResponse(
                user = user.toResponse(),
                accessToken = accessToken
            )
    }
}
