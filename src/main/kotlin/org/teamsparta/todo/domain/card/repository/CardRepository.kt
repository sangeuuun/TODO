package org.teamsparta.todo.domain.card.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.teamsparta.todo.domain.card.model.Card

interface CardRepository : JpaRepository<Card, Long> {
}