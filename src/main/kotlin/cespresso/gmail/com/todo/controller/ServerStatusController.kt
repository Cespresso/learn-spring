package cespresso.gmail.com.todo.controller

import cespresso.gmail.com.todo.security.security_model.FirebaseUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/server")
class ServerStatusController {
    @GetMapping()
    fun hello():String?{
        return "正常に起動しています"
    }
}