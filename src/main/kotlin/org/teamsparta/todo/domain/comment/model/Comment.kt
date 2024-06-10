package org.teamsparta.todo.domain.comment.model

import org.teamsparta.todo.domain.todo.model.Todo
import jakarta.persistence.*
import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.user.model.User

@Entity
@Table(name = "comment")
class Comment (

    var content: String,

    var password: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}