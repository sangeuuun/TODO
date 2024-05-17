package org.teamsparta.todo.domain.card.service

import org.teamsparta.todo.domain.card.dto.CardResponse
import org.teamsparta.todo.domain.card.dto.CreateCardRequest
import org.teamsparta.todo.domain.card.dto.UpdateCardRequest

interface CardService {
    fun getAllCardList(): List<CardResponse>

    fun getCardById(cardId: Long): CardResponse

    fun createCard(request: CreateCardRequest): CardResponse

    fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse

    fun deleteCard(cardId: Long)
}