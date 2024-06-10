package org.teamsparta.todo.domain.todo.dto

import jakarta.validation.constraints.Size
import org.teamsparta.todo.domain.todo.model.Todo
import org.teamsparta.todo.domain.user.model.User

data class TodoRequest(

    @field:Size(min = 1, max = 200)
    val title: String,

    @field:Size(min = 1, max = 1000)
    val content: String
)

fun TodoRequest.toEntity(user: User): Todo {
    return Todo(
        title = this.title,
        content = this.content,
        user = user
    )
}