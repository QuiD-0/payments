package com.quid.unit.security.config

import com.quid.unit.security.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class AuthenticationConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/token/issue/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(JwtTokenFilter(), BasicAuthenticationFilter::class.java)
            .csrf().disable()
            .build()

}
