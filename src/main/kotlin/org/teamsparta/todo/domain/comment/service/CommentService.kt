package org.teamsparta.todo.domain.comment.service

import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.comment.dto.DeleteCommentRequest
import org.teamsparta.todo.domain.comment.dto.CommentRequest
import org.teamsparta.todo.infra.security.AuthUser

interface CommentService {

    fun writeComment(todoId: Long, authUser: AuthUser, request: CommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId: Long, authUser: AuthUser, request: CommentRequest): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long, authUser: AuthUser, request: DeleteCommentRequest)

}