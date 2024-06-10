package org.teamsparta.todo.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.security.crypto.password.PasswordEncoder
import org.teamsparta.todo.domain.user.model.User

data class SignUpRequest(
    @Email
    @field:NotBlank
    val email: String,

    @field:Length(min = 6, max = 15)
    val password: String,

    @field:Length(min = 2, max = 10)
    val nickname: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder): User {
        return User(
            email = this.email,
            password = passwordEncoder.encode(password),
            nickname = this.nickname,
        )
    }
}
