package cespresso.gmail.com.todo.domain.repository

import cespresso.gmail.com.todo.domain.entity.Todos
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todos, Long>