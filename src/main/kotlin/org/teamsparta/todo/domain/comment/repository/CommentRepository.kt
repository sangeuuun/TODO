package org.teamsparta.todo.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.teamsparta.todo.domain.comment.model.Comment

interface CommentRepository: JpaRepository<Comment, Long> {
}