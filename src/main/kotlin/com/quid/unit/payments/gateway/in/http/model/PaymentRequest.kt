package com.quid.unit.payments.gateway.`in`.http.model

import com.quid.unit.payments.domain.Card
import com.quid.unit.payments.domain.Payment

data class PaymentRequest(
    val requestId: String,
    val identifier: String,
    val price: Int,
    val card: Card,
    ) {
    fun toDomain(): Payment {
        if (requestId.isBlank()) throw IllegalArgumentException("requestId is blank")
        if (identifier.isBlank()) throw IllegalArgumentException("qualifier is blank")
        if (price <= 0) throw IllegalArgumentException("price is not positive")
        return Payment.create(requestId, identifier, card, price)
    }
}