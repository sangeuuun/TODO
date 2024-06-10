package org.teamsparta.todo.domain.comment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.comment.dto.DeleteCommentRequest
import org.teamsparta.todo.domain.comment.dto.CommentRequest
import org.teamsparta.todo.domain.comment.model.Comment
import org.teamsparta.todo.domain.comment.repository.CommentRepository
import org.teamsparta.todo.domain.todo.repository.TodoRepository
import org.teamsparta.todo.domain.user.repository.UserRepository
import org.teamsparta.todo.exception.ModelNotFoundException
import org.teamsparta.todo.exception.UnauthorizedException
import org.teamsparta.todo.infra.security.AuthUser

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : CommentService {

    @Transactional
    override fun writeComment(todoId: Long, authUser: AuthUser, request: CommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val user = userRepository.findByIdOrNull(authUser.id) ?: throw ModelNotFoundException("User", authUser.id)

        val comment = Comment(
                content = request.content,
                password = passwordEncoder.encode(request.password),
                todo = todo,
                user = user
            )
        todo.addComment(comment)

        return CommentResponse.from(commentRepository.save(comment))

    }

    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, authUser: AuthUser, request: CommentRequest): CommentResponse {
        todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        var comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        val user = userRepository.findByIdOrNull(authUser.id) ?: throw ModelNotFoundException("User", authUser.id)

        if (comment.user.id != authUser.id || !passwordEncoder.matches(request.password, comment.password))
            throw UnauthorizedException("권한이 없습니다.")

        comment.content = request.content

        return CommentResponse.from(commentRepository.save(comment))
    }

    @Transactional
    override fun deleteComment(todoId: Long, commentId: Long, authUser: AuthUser, request: DeleteCommentRequest) {
        todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val user = userRepository.findByIdOrNull(authUser.id) ?: throw ModelNotFoundException("User", authUser.id)
        var comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (comment.user.id != authUser.id || !passwordEncoder.matches(request.password, comment.password))
            throw UnauthorizedException("권한이 없습니다.")

        commentRepository.delete(comment)
    }
}