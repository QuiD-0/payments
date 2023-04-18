package com.quid.unit.token.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
        assertEquals("quid", token.getsub())
    }

    @Test
    fun validate() {
        val token = Token.generate("quid")
        assertEquals(true, token.validate())
    }
}