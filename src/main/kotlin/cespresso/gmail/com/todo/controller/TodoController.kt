package cespresso.gmail.com.todo.controller

import cespresso.gmail.com.todo.domain.dto.DiscordChat
import cespresso.gmail.com.todo.domain.entity.Todos
import cespresso.gmail.com.todo.domain.repository.TodoRepository
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/Todos")
class TodoController(val repository: TodoRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping
    fun addCustomer(@RequestBody todos: Todos){
        repository.save(todos)
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


    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody todos: Todos) {
        assert(todos.id == id)
        repository.save(todos)
    }

    @DeleteMapping("/{id}")
    fun removeCustomer(@PathVariable id: Long)
            = repository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long)
            = repository.findById(id)

}