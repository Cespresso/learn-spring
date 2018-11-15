package cespresso.gmail.com.todo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
class ServerStatusController {
    @GetMapping("/hello")
    fun hello():String{
        return "hello"
    }
}