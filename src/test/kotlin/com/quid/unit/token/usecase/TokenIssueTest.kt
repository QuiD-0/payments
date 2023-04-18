package com.quid.unit.token.usecase

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TokenIssueTest {

    private val token = TokenIssue.TokenIssueUseCase()

    @Test
    fun issueToken() {
        val token = token.issue("quid")
        assertNotNull(token)
    }
}