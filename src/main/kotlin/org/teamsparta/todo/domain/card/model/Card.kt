package org.teamsparta.todo.domain.card.model

import jakarta.persistence.*
import org.teamsparta.todo.domain.card.dto.CardResponse
import java.time.LocalDateTime

@Entity
@Table(name = "card")
class Card(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: LocalDateTime = LocalDateTime.now().plusHours(9),

    @Column(name = "name", nullable = false)
    var name: String,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        content = content,
        date = date,
        name = name
    )
}