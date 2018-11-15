package cespresso.gmail.com.todo.domain.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class FireBaseAdmin {

    val serviceAccount = FileInputStream("hostingtest-4d77a-firebase-adminsdk-o0slq-172456ec2e.json")

    val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://hostingtest-4d77a.firebaseio.com")
            .build()

    val firebaseApp = FirebaseApp.initializeApp(options)
    @Bean
    fun getFireBaseAdmin():FirebaseApp = firebaseApp
}