package org.teamsparta.todo.domain.comment.service

import org.teamsparta.todo.domain.comment.dto.CommentRequest
import org.teamsparta.todo.domain.comment.dto.CommentResponse
import org.teamsparta.todo.domain.comment.dto.DeleteCommentRequest

interface CommentService {

    fun writeComment(todoId: Long, request: CommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long, request: DeleteCommentRequest)

}