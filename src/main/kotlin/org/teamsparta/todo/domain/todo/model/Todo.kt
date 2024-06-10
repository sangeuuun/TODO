package org.teamsparta.todo.domain.todo.model

import jakarta.persistence.*
import org.teamsparta.todo.domain.comment.model.Comment
import org.teamsparta.todo.domain.todo.dto.TodoRequest
import org.teamsparta.todo.domain.user.model.User
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

    @Column(name = "status", nullable = false)
    var status: Boolean = false,

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }

    fun updateTodo(request: TodoRequest) {
        title = request.title
        content = request.content
    }
}