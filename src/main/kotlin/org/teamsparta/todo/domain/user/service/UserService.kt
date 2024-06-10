package org.teamsparta.todo.domain.user.service

import org.teamsparta.todo.domain.user.dto.LoginRequest
import org.teamsparta.todo.domain.user.dto.LoginResponse
import org.teamsparta.todo.domain.user.dto.SignUpRequest
import org.teamsparta.todo.domain.user.dto.SignUpResponse

interface UserService {
    fun signUp(request: SignUpRequest): SignUpResponse
    fun login(request: LoginRequest): LoginResponse

}