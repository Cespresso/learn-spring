package cespresso.gmail.com.todo.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig :WebSecurityConfigurerAdapter(){

    @Autowired
    lateinit var authenticationProvider: FirebaseAuthenticationProvider
    override fun authenticationManager(): AuthenticationManager {
        //TODO
        return ProviderManager(Arrays.asList(authenticationProvider))
    }

    override fun configure(http: HttpSecurity?) {
        if(http==null)return
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/public").permitAll()
                    .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)

    }

    private fun authenticationTokenFilterBean():FirebaseAuthenticationTokenFilter {
        val authenticationTokenFilter = FirebaseAuthenticationTokenFilter()
        authenticationTokenFilter.setAuthenticationManager(authenticationManager())
//        authenticationTokenFilter.setAuthenticationSuccessHandler((req,res,atu)->{})
        return authenticationTokenFilter
    }
}