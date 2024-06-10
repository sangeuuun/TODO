package org.teamsparta.todo.domain.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.teamsparta.todo.domain.user.dto.LoginRequest
import org.teamsparta.todo.domain.user.dto.LoginResponse
import org.teamsparta.todo.domain.user.dto.SignUpRequest
import org.teamsparta.todo.domain.user.dto.SignUpResponse
import org.teamsparta.todo.domain.user.repository.UserRepository
import org.teamsparta.todo.infra.security.JwtTokenPlugin

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenPlugin: JwtTokenPlugin,
): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): SignUpResponse {
        if(userRepository.existsByEmail(request.email)) throw IllegalStateException("이미 존재하는 메일입니다.")
        return SignUpResponse.from(userRepository.save(request.toEntity(passwordEncoder)))
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw IllegalArgumentException("잘못된 Email/PW 입니다.")
        if (!user.isValidPassword(
                request.password,
                passwordEncoder
            )
        ) throw IllegalArgumentException("잘못된 Email/PW 입니다.")
        val accessToken = jwtTokenPlugin.generateAccessToken(user)

        return LoginResponse.from(user, accessToken)
    }
}