package org.teamsparta.todo.domain.user.model

import jakarta.persistence.*
import org.teamsparta.todo.domain.user.dto.UserResponse

@Entity
@Table(name = "app_user")
class User (

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "nickname", nullable = false)
    val nickname: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        name = nickname
    )
}