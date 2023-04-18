package com.quid.unit.token.domain

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*

class Token(private val token: String) {

    fun token() = token

    companion object {
        private const val SECRET_KEY = "quid-payment-test-secret-key-2023-04.secret"
        private val KEY = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))

        fun generate(identifier: String): Token {
            val claims = Jwts.claims().setSubject(identifier)
            val now = Instant.now()
            val compact = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .signWith(KEY)
                .compact()
            return Token(compact)
        }
    }

}