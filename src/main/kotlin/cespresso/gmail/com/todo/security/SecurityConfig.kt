package cespresso.gmail.com.todo.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig :WebSecurityConfigurerAdapter(){

    @Autowired
    lateinit var authenticationProvider: AuthenticationProvider
    override fun authenticationManager(): AuthenticationManager {
        //TODO
        return ProviderManager(Arrays.asList(authenticationProvider))
    }

    override fun configure(http: HttpSecurity?) {
        if(http==null)return
        http.cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/public").permitAll()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/todo/**").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationTokenFilterBean("/user/**"), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(authenticationTokenFilterBean("/todo/**"), UsernamePasswordAuthenticationFilter::class.java)

    }

    private fun authenticationTokenFilterBean(filterUrl:String):FirebaseAuthenticationTokenFilter {
        val authenticationTokenFilter = FirebaseAuthenticationTokenFilter(filterUrl)
        authenticationTokenFilter.setAuthenticationManager(authenticationManager())
        // この処理を追加しないと
        authenticationTokenFilter.setAuthenticationSuccessHandler({ request, response, authentication -> })
        return authenticationTokenFilter
    }
    // TODO FilterRegistrationBean CorsFilterについても検討
    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("*") //TODO 後で設置するhostのoriginに変更
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource: UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}