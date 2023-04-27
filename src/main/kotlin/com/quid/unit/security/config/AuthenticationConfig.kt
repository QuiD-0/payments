package com.quid.unit.security.config

import com.quid.unit.security.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.filter.OncePerRequestFilter


@Configuration
@EnableWebSecurity
class AuthenticationConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(JwtTokenFilter(), OncePerRequestFilter::class.java)
            .csrf().disable()
            .build()


    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer = WebSecurityCustomizer { web: WebSecurity ->
        web.ignoring().requestMatchers("/token/issue/**", "/")
    }

}
