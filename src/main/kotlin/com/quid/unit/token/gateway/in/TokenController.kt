package com.quid.unit.token.gateway.`in`

import com.quid.unit.token.domain.Token
import com.quid.unit.token.usecase.TokenIssue
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/token")
class TokenController(private val tokenIssue : TokenIssue) {
    private val logger = LoggerFactory.getLogger(TokenController::class.java)

    @GetMapping("/issue/{identifier}")
    fun issueToken(@PathVariable(name = "identifier") identifier : String) : Token {
        logger.info("Token issue request received: $identifier")
        return tokenIssue(identifier)
    }

}