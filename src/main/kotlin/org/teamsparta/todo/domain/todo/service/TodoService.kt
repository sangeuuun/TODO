package org.teamsparta.todo.domain.todo.service

import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.todo.dto.TodosResponse
import org.teamsparta.todo.infra.security.AuthUser

interface TodoService {
    fun getAllTodoList(sort: String?, authorName: String?): List<TodoResponse>

    fun getTodoById(todoId: Long): TodosResponse

    fun createTodo(authUser: AuthUser, request: TodoRequest): TodoResponse

    fun updateTodo(authUser: AuthUser, todoId: Long, request: TodoRequest): TodosResponse

    fun updateTodoStatus(authUser: AuthUser, todoId: Long, status: Boolean): TodosResponse

    fun deleteTodo(authUser: AuthUser, todoId: Long)
}