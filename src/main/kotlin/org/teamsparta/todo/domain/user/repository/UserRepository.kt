package org.teamsparta.todo.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.teamsparta.todo.domain.user.model.User

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}