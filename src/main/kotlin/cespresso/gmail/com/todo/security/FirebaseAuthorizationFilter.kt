package cespresso.gmail.com.todo.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class FirebaseAuthorizationFilter(val myAuthenticationManager: AuthenticationManager):BasicAuthenticationFilter(myAuthenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")

        if(header ==null || !header.startsWith( "Bearer ")){
            chain.doFilter(request,response)
        }
//        val authentication
        SecurityContextHolder.getContext().authentication
    }
}