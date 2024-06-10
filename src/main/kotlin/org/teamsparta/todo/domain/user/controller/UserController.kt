package org.teamsparta.todo.domain.user.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.teamsparta.todo.domain.user.dto.LoginRequest
import org.teamsparta.todo.domain.user.dto.LoginResponse
import org.teamsparta.todo.domain.user.dto.SignUpRequest
import org.teamsparta.todo.domain.user.dto.SignUpResponse
import org.teamsparta.todo.domain.user.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signUp")
    fun signUp(@Valid @RequestBody request: SignUpRequest): ResponseEntity<SignUpResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(request))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(request))
    }
}