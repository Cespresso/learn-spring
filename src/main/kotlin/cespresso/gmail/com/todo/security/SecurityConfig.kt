package cespresso.gmail.com.todo.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig :WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity) {
        http.cors()
                .and().authorizeRequests()
                .antMatchers("/public").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
//                .addFilter()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }
}