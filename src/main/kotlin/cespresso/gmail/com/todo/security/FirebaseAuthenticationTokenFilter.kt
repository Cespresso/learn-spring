package cespresso.gmail.com.todo.security

import cespresso.gmail.com.todo.security.security_exception.NotFoundAuthorizationHeaderException
import cespresso.gmail.com.todo.security.security_model.FirebaseAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.FilterChain




//TODO ここのコンストラクタについて調べる
class FirebaseAuthenticationTokenFilter:AbstractAuthenticationProcessingFilter("/user/**") {
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        // Authorization を取り出す
        println("どうしたんンお")
        val header = request.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw NotFoundAuthorizationHeaderException("ヘッダーが正しい形式でありません"); //TODO nullを返していいのかを調べる
        }
        // Bearer以下を取り出す
        println("認証の確認")
        val authToken = header.replace(TOKEN_PREFIX,"")
        return authenticationManager.authenticate(FirebaseAuthenticationToken(authToken))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        super.successfulAuthentication(request, response, chain, authResult)

        // 通常認証に成功した場合rootへリダイレクトされる
        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain!!.doFilter(request, response)
    }
}