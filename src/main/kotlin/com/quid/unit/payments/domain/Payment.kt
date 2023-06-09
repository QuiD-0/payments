package com.quid.unit.payments.domain

import java.time.LocalDateTime

class Payment(
    val paymentId: String? = null,
    val requestId: String,
    val identifier: String,
    val card: Card,
    val price: Int,
    val payStatus: PayStatus,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    fun copy(payStatus: PayStatus): Payment {
        return Payment(
            paymentId,
            requestId,
            identifier,
            card,
            price,
            payStatus,
            createdAt,
        )
    }

    companion object {
        fun create(requestId: String, identifier: String, card: Card, price: Int): Payment {
            return Payment(
                requestId = requestId,
                identifier = identifier,
                card = card,
                payStatus = PayStatus.PAYMENT_WAITING,
                price = price,
            )
        }

        fun of(
            id: String,
            requestId: String,
            identifier: String,
            card: Card,
            price: Int,
            payStatus: PayStatus,
            createdAt: LocalDateTime
        ): Payment {
            return Payment(id, requestId, identifier, card, price, payStatus, createdAt)
        }
    }
}