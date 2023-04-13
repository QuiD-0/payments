package com.quid.payments.domain

import java.time.LocalDateTime

class Payment(
    val paymentId: String? = null,
    val requestId: String,
    val qualifier: String,
    val card: Card,
    val price: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object{
        fun create(requestId: String, qualifier: String, card: Card, price: Int): Payment {
            return Payment(
                requestId = requestId,
                qualifier = qualifier,
                card = card,
                price = price,
            )
        }

        fun of(
            id: String?,
            requestId: String,
            qualifier: String,
            card: Card,
            price: Int,
            createdAt: LocalDateTime
        ): Payment {
            return Payment(id, requestId, qualifier, card, price, createdAt)
        }
    }
}