package org.teamsparta.todo.domain.todo.service

import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest

interface TodoService {
    fun getAllTodoList(): List<TodoResponse>

    fun getTodoById(todoId: Long): TodoResponse

    fun createTodo(request: TodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: TodoRequest): TodoResponse

    fun updateTodoStatus(todoId: Long, status: Boolean): TodoResponse

    fun deleteTodo(todoId: Long)
}