package org.teamsparta.todo.domain.card.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.teamsparta.todo.domain.card.dto.CardResponse
import org.teamsparta.todo.domain.card.dto.CreateCardRequest
import org.teamsparta.todo.domain.card.dto.UpdateCardRequest
import org.teamsparta.todo.domain.card.model.Card
import org.teamsparta.todo.domain.card.model.toResponse
import org.teamsparta.todo.domain.card.repository.CardRepository

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
): CardService {

    override fun getAllCardList(): List<CardResponse> {
        return cardRepository.findAll().map { it.toResponse() }

    }

    override fun getCardById(cardId: Long): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw EntityNotFoundException("GetCard with ID $cardId not found")
        return card.toResponse()
    }

    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        return cardRepository.save<Card?>(
            Card(
                title = request.title,
                content = request.content,
                name = request.name
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw EntityNotFoundException("UpdateCard with ID $cardId not found")
        return card.toResponse()

        val (title, name, content) = request
        card.title = title
        card.content = content
        card.name = name

        return cardRepository.save(card).toResponse()
    }

    @Transactional
    override fun deleteCard(cardId: Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw EntityNotFoundException("DeleteCard with ID $cardId not found")
        cardRepository.delete(card)
    }

}