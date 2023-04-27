package com.quid.unit.security

import com.quid.unit.token.domain.Token
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtTokenFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain,
    ) {
        if (urls.any { request.requestURI.contains(it) }) {
            filterChain.doFilter(request, response)
            return
        }

        val header: String = try {
            request.getHeader(HttpHeaders.AUTHORIZATION)
        } catch (e: Exception) {
            throw IllegalArgumentException("Authorization header is missing")
        }

        if (!header.startsWith("Bearer ")) throw IllegalArgumentException("bearer token is missing")

        val token = Token(header.substring(7)).also { it.validate() }

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
            token.sub(), token.getToken()
        )

        filterChain.doFilter(request, response)
    }

    companion object {
        private val urls = listOf(
            "/token/issue",
        )
    }
}