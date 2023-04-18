package com.quid.unit.token.usecase

import com.quid.unit.token.domain.Token
import org.springframework.stereotype.Service

interface TokenIssue {
    fun issue(identifier: String): Token

    @Service
    class TokenIssueUseCase : TokenIssue {
        override fun issue(identifier: String): Token {
            return Token.generate(identifier)
        }
    }
}