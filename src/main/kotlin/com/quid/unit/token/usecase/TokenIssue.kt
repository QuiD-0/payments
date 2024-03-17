package com.quid.unit.token.usecase

import com.quid.unit.token.domain.Token
import org.springframework.stereotype.Service

fun interface TokenIssue {
    operator fun invoke(identifier: String): Token

    @Service
    class TokenIssueUseCase : TokenIssue {
        override fun invoke(identifier: String): Token = Token.generate(identifier)
    }
}