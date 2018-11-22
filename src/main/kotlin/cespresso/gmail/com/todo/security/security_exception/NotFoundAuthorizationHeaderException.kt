package cespresso.gmail.com.todo.security.security_exception

import org.springframework.security.core.AuthenticationException

class NotFoundAuthorizationHeaderException(val msg:String) : AuthenticationException(msg) {
}