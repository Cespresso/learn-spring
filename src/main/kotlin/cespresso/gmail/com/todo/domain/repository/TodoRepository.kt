package cespresso.gmail.com.todo.domain.repository

import cespresso.gmail.com.todo.domain.entity.Todos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TodoRepository : JpaRepository<Todos, Long>{
//    @Query("SELECT * From Todos WHERE user_id = :user_id")
//    fun findAllTodosByUserID(@Param("user_id") user_id:String):java.util.List<Todos>
}