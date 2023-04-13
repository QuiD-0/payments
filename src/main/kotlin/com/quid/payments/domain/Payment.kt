package com.quid.payments.domain

import java.time.LocalDateTime

class Payment(
    val paymentId: String? = null,
    val requestId: String,
    val identifier: String,
    val card: Card,
    val price: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object{
        fun create(requestId: String, identifier: String, card: Card, price: Int): Payment {
            return Payment(
                requestId = requestId,
                identifier = identifier,
                card = card,
                price = price,
            )
        }

        fun of(
            id: String?,
            requestId: String,
            identifier: String,
            card: Card,
            price: Int,
            createdAt: LocalDateTime
        ): Payment {
            return Payment(id, requestId, identifier, card, price, createdAt)
        }
    }
}