package com.quid.unit.token.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TokenTest {

    @Test
    fun generate() {
        val token = Token.generate("quid")
        assertNotNull(token)
    }

    @Test
    fun getIdentifier() {
        val token = Token.generate("quid")
        assertEquals("quid", token.sub())
    }

    @Test
    fun validate() {
        val token = Token.generate("quid")
        assertDoesNotThrow { token.validate() }
    }
}