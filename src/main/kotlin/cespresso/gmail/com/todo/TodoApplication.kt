package cespresso.gmail.com.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@SpringBootApplication
@EnableScheduling
class TodoApplication

fun main(args: Array<String>) {
    runApplication<TodoApplication>(*args)
}

@Bean
fun corsFilter(): CorsFilter {
    val source = UrlBasedCorsConfigurationSource()
    val config = CorsConfiguration()
    config.allowCredentials = true // you USUALLY want this
    config.addAllowedOrigin("*")
    config.addAllowedHeader("*")
    config.addAllowedMethod("GET")
    config.addAllowedMethod("PUT")
    config.addAllowedMethod("POST")
    config.addAllowedHeader("Authorization")
    source.registerCorsConfiguration("/**", config)
    return CorsFilter(source)
}

