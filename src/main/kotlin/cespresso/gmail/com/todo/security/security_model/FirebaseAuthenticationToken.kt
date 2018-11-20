package cespresso.gmail.com.todo.security.security_model

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import java.util.*


// TODO このクラスのコンストラクタについて調べる
class FirebaseAuthenticationToken(val token:String):UsernamePasswordAuthenticationToken(null,null) {
    companion object {
        val versionID = 1L
    }
}