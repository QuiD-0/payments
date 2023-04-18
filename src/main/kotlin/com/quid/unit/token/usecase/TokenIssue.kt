package com.quid.unit.token.usecase

import com.quid.unit.token.domain.Token

interface TokenIssue {
    fun issue(identifier: String): Token
}