package cespresso.gmail.com.todo.security

import cespresso.gmail.com.todo.security.security_model.FirebaseAuthenticationToken
import cespresso.gmail.com.todo.security.security_model.FirebaseUserDetails
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.session.SessionAuthenticationException
import org.springframework.stereotype.Component


@Component
class FirebaseAuthenticationProvider:AbstractUserDetailsAuthenticationProvider() {

    @Autowired
    lateinit var firebaseAuth:FirebaseAuth

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails? {
        if(authentication is FirebaseAuthenticationToken){
            val task = firebaseAuth.verifyIdTokenAsync(authentication.token)
            return try{
                val token = task.get()
                FirebaseUserDetails(token.email,token.uid)
            }catch (e:Exception){
                throw SessionAuthenticationException(e.message)
            }
        }
        throw SessionAuthenticationException("Invalid authentication class")
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        // TODO このメソッドについて調べる
    }
}