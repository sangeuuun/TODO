package org.teamsparta.todo.domain.comment.model

import org.teamsparta.todo.domain.todo.model.Todo
import jakarta.persistence.*
import org.teamsparta.todo.domain.comment.dto.CommentResponse

@Entity
@Table(name = "comment")
class Comment (

    var content: String,

    val password: String,

    val nickname: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    val todo: Todo,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        content = content,
        nickname = nickname,
        todoId = todo.id!!
    )
}