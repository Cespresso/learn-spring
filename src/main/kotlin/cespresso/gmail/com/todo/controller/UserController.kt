package cespresso.gmail.com.todo.controller

import cespresso.gmail.com.todo.security.security_model.FirebaseUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @GetMapping("/status")
    fun status():String{
        return "user"
    }

    @GetMapping("/profile")
    fun profile():FirebaseUserDetails?{
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetail = authentication.principal
        if(userDetail is FirebaseUserDetails){
            println(authentication.authorities)
            return userDetail
        }
        return null
    }
}