package org.teamsparta.todo.domain.todo.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.todo.dto.TodosResponse
import org.teamsparta.todo.domain.todo.service.TodoService
import org.teamsparta.todo.infra.security.AuthUser
import org.teamsparta.todo.infra.security.RequestUser

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    @GetMapping
    fun getTodoList(
        @RequestParam sort: String?,
        @RequestParam authorName: String?
        ): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList(sort, authorName))
    }

    @PostMapping
    fun createTodo(
        @RequestUser @Parameter(hidden = true) authUser: AuthUser,
        @RequestBody @Valid createCardRequest: TodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(authUser, createCardRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestUser @Parameter(hidden = true) authUser: AuthUser,
        @RequestBody @Valid updateCardRequest: TodoRequest
    ): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(authUser, todoId, updateCardRequest))
    }

    @PatchMapping("/{todoId}")
    fun updateTodoStatus(
        @PathVariable todoId: Long,
        @RequestUser @Parameter(hidden = true) authUser: AuthUser,
        @RequestParam status: Boolean
    ): ResponseEntity<TodosResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoStatus(authUser, todoId, status))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @RequestUser @Parameter(hidden = true) authUser: AuthUser,
        @PathVariable todoId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}