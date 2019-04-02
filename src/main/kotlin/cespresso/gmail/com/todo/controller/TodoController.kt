package cespresso.gmail.com.todo.controller

import cespresso.gmail.com.todo.domain.dto.DiscordChat
import cespresso.gmail.com.todo.domain.entity.Todos
import cespresso.gmail.com.todo.domain.repository.TodoRepository
import cespresso.gmail.com.todo.security.security_model.FirebaseUserDetails
import org.springframework.data.domain.Example
import org.springframework.http.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/todo")
class TodoController(val repository: TodoRepository) {

    @GetMapping
    fun findAll():MutableList<Todos>?{
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if (userDetail is FirebaseUserDetails) {
            println(authentication.authorities)
//            return repository.findAllTodosByUserID(userDetail.id)
            val example = Example.of(Todos(user_id = userDetail.id))
            return repository.findAll(example)
        }
        return null

    }
    @GetMapping("/all")
    fun findAllTodo():MutableList<Todos>?{
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if (userDetail is FirebaseUserDetails) {
            println(authentication.authorities)
            val example = Example.of(Todos(user_id = userDetail.id))
            return try{
                repository.findAll(example)
            } catch (e:Exception){
                null
            }
        }
        return null
    }
    @GetMapping("/normal")
    fun findAllNormal()=repository.findAll()


    @PostMapping
    fun addTodo(@RequestBody todos: Todos){
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if (userDetail is FirebaseUserDetails) {
            todos.user_id = userDetail.id
            todos.id = null
            println(todos.user_id)
            repository.save(todos)
        }

    }

//    @GetMapping("/{id}/send")
//    fun sendTodo(@PathVariable id: Long){
//        val todo = repository.findById(id)
//        val restTemplate = RestTemplate()
//        val requestEntity = RequestEntity
//                .post(URI("https://discordapp.com/api/webhooks/508556713687777280/i9aOBqOoo6wWMqSSlQWjNO-p6vMNz1ZcNc9xjgXIhwsqMZDeFONGxbPqqfteetJDnnwg"))
//                .contentType(MediaType.APPLICATION_JSON) // (1)
//                .body<DiscordChat>(DiscordChat("test"))
//        println(requestEntity.url.toString())
//        println(requestEntity.body.toString())
//        restTemplate.exchange(requestEntity,String.javaClass)
//    }

//    @GetMapping("/{id}/send")
//    fun send2Todo(@PathVariable id: Long){
//        val todo = repository.findById(id).orElse(Todos())
//        val restTemplate = RestTemplate()
//        val headers = HttpHeaders()
//        headers.accept = mutableListOf(MediaType.APPLICATION_JSON)
//        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
//        val entity = HttpEntity(DiscordChat(todo.title), headers)
//
//        restTemplate.exchange("https://discordapp.com/api/webhooks/508556713687777280/i9aOBqOoo6wWMqSSlQWjNO-p6vMNz1ZcNc9xjgXIhwsqMZDeFONGxbPqqfteetJDnnwg", HttpMethod.POST,entity,String.javaClass)
//    }


    @PostMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody todo: Todos) {
        if(id !=todo.id){
            // エンティティとパラメータで指定されたIDが違う
            throw Exception("")
        }
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if (userDetail is FirebaseUserDetails) {
            val example = Example.of(Todos(id = todo.id,user_id = userDetail.id))
            val beforeTodos = repository.findAll(example)
            if(beforeTodos.count() != 1) throw Exception("存在しない対象の更新")
            println(todo.user_id)
            val beforeTodo = beforeTodos[0]
            beforeTodo.title = todo.title
            beforeTodo.body = todo.body
            beforeTodo.completed = todo.completed
            repository.save(beforeTodo)
        }

    }

    @DeleteMapping("/{id}")
    fun removeCustomer(@PathVariable id: Long){
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if (userDetail is FirebaseUserDetails) {
            val example = Example.of(Todos(id = id, user_id = userDetail.id))
            val beforeTodos = repository.findAll(example)
            if (beforeTodos.count() != 1) throw Exception("存在しない対象の削除")
            repository.deleteById(id)
        }
    }
}