package org.teamsparta.todo.domain.todo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.teamsparta.todo.domain.todo.model.Todo

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByOrderByDateAsc(): List<Todo>
    fun findAllByOrderByDateDesc(): List<Todo>

    @Query("select t from Todo t left join User u on t.user.id = u.id where u.nickname = ?1")
    fun findAllByNickName(authorName: String): List<Todo>
}