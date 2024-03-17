package com.quid.unit.token.domain

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*


data class Token(
    val token: String
) {

    fun validate() = if (extractAllClaims().id != SERVER_ID)
        throw IllegalArgumentException("Invalid token") else Unit

    fun sub(): String = extractAllClaims().subject

    private fun extractAllClaims(): Claims =
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).body

    companion object {
        private const val SERVER_ID = "payment"
        private const val SECRET_KEY = "quid-payment-test-secret-key-2023-04.secret"
        private val KEY = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))

        fun generate(identifier: String): Token {
            val claims = Jwts.claims().setId(SERVER_ID).setSubject(identifier)
            val now = Instant.now()
            val compact =
                    Jwts.builder().setClaims(claims).setIssuedAt(Date.from(now)).signWith(KEY).compact()
            return Token(compact)
        }
    }
}