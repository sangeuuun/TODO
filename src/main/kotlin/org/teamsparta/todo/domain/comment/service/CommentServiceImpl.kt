package org.teamsparta.todo.domain.comment.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.teamsparta.todo.domain.comment.dto.CommentRequest
import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.comment.dto.DeleteCommentRequest
import org.teamsparta.todo.domain.comment.model.Comment
import org.teamsparta.todo.domain.comment.model.toResponse
import org.teamsparta.todo.domain.comment.repository.CommentRepository
import org.teamsparta.todo.domain.todo.model.Todo
import org.teamsparta.todo.domain.todo.model.toResponse
import org.teamsparta.todo.domain.todo.repository.TodoRepository

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {

    override fun writeComment(todoId: Long, request: CommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")

        val comment = Comment(
                nickname = request.nickname,
                content = request.content,
                password = request.password,
                todo = todo
            )
        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse()

    }

    override fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")
        var comment = commentRepository.findByIdOrNull(commentId) ?: throw EntityNotFoundException("Comment with ID $commentId not found")

        if (request.nickname == comment.nickname && request.password == comment.password) {
            comment.content = request.content
            return commentRepository.save(comment).toResponse()
        } else throw IllegalArgumentException("수정 권한이 없습니다.")
    }

    override fun deleteComment(todoId: Long, commentId: Long, request: DeleteCommentRequest) {
        todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException("Todo with ID $todoId not found")
        var comment = commentRepository.findByIdOrNull(commentId) ?: throw EntityNotFoundException("Comment with ID $commentId not found")

        if (request.nickname == comment.nickname && request.password == comment.password) {
            commentRepository.delete(comment)
        } else throw IllegalArgumentException("삭제 권한이 없습니다.")
    }
}