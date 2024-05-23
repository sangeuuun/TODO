package org.teamsparta.todo.domain.todo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.todo.service.TodoService

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/{todoId}")
    fun getCard(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    @GetMapping
    fun getCardList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList())
    }

    @PostMapping
    fun createCard(@RequestBody createCardRequest: TodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createCardRequest))
    }

    @PutMapping("/{todoId}")
    fun updateCard(
        @PathVariable todoId: Long,
        @RequestBody updateCardRequest: TodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateCardRequest))
    }

    @PatchMapping("/{todoId}")
    fun updateCardStatus(
        @PathVariable todoId: Long,
        @RequestParam status: Boolean
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoStatus(todoId, status))
    }

    @DeleteMapping("/{todoId}")
    fun deleteCard(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}