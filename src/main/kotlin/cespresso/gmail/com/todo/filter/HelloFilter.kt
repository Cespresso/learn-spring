package cespresso.gmail.com.todo.filter

import org.jboss.logging.Logger
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import com.google.firebase.FirebaseApp
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Autowired
import java.io.FileInputStream


//
//@Component
//class HelloFilter:Filter  {
//
//    @Autowired
//    lateinit var firebaseApp: FirebaseApp
//
//    val logger = Logger.getLogger(this.javaClass)
//    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain?) {
//        logger.info("フィルター処理前")
//        logger.info(firebaseApp.name)
////        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
////            chain.doFilter(request, response)
////            return
////        }
//        chain?.doFilter(request,response)
//        logger.info("フィルター処理後")
//    }
//
//}