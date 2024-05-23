package org.teamsparta.todo.domain.todo.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.todo.model.Todo
import org.teamsparta.todo.domain.todo.model.toResponse
import org.teamsparta.todo.domain.todo.repository.TodoRepository

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
): TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }

    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: TodoRequest): TodoResponse {
        return todoRepository.save<Todo?>(
            Todo(
                title = request.title,
                content = request.content,
                name = request.name
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: TodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")

        val (title, name, content) = request
        todo.title = title
        todo.content = content
        todo.name = name

        return todoRepository.save(todo).toResponse()
    }

    override fun updateTodoStatus(todoId: Long, status: Boolean): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")

        todo.status = status

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")
        todoRepository.delete(todo)
    }

}