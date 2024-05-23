package org.teamsparta.todo.domain.todo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.teamsparta.todo.domain.todo.model.Todo

interface TodoRepository : JpaRepository<Todo, Long> {
}