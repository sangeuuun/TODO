package org.teamsparta.todo.domain.todo.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.todo.dto.TodosResponse
import org.teamsparta.todo.domain.todo.dto.toEntity
import org.teamsparta.todo.domain.todo.repository.TodoRepository
import org.teamsparta.todo.domain.user.repository.UserRepository
import org.teamsparta.todo.exception.ModelNotFoundException
import org.teamsparta.todo.exception.UnauthorizedException
import org.teamsparta.todo.infra.security.AuthUser

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
): TodoService {

    override fun getAllTodoList(sort: String?, authorName: String?): List<TodoResponse> {
        authorName?.let{
            return todoRepository.findAllByNickName(authorName).map { TodoResponse.from(it) }
        }

        return if(sort=="desc") {
            todoRepository.findAllByOrderByDateDesc()
        } else {
            todoRepository.findAllByOrderByDateAsc()
        }.map { TodoResponse.from(it) }
    }

    override fun getTodoById(todoId: Long): TodosResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return TodosResponse.from(todo)
    }

    @Transactional
    override fun createTodo(authUser: AuthUser, request: TodoRequest): TodoResponse {
        val user = userRepository.findByIdOrNull(authUser.id) ?: throw ModelNotFoundException("User", authUser.id)
        return TodoResponse.from(todoRepository.save(request.toEntity(user)))
    }

    @Transactional
    override fun updateTodo(authUser: AuthUser, todoId: Long, request: TodoRequest): TodosResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        if (todo.user.id != authUser.id)
            throw UnauthorizedException("권한이 없습니다.")

        todo.updateTodo(request)

        return TodosResponse.from(todoRepository.save(todo))
    }

    @Transactional
    override fun updateTodoStatus(authUser: AuthUser, todoId: Long, status: Boolean): TodosResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        if (todo.user.id != authUser.id)
            throw UnauthorizedException("권한이 없습니다.")

        todo.status = status

        return TodosResponse.from(todoRepository.save(todo))
    }

    @Transactional
    override fun deleteTodo(authUser: AuthUser, todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        if (todo.user.id != authUser.id)
            throw UnauthorizedException("권한이 없습니다.")

        todoRepository.delete(todo)
    }

}