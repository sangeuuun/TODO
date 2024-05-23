package org.teamsparta.todo.domain.todo.model

import jakarta.persistence.*
import org.teamsparta.todo.domain.todo.dto.TodoResponse
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: LocalDateTime = LocalDateTime.now().plusHours(9),

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "status", nullable = false)
    var status: Boolean = false

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        content = content,
        date = date,
        name = name,
        status = status
    )
}