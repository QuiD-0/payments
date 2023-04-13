package com.quid.payments.gateway.`in`.http.model

import com.quid.payments.domain.Card
import com.quid.payments.domain.Payment

data class PaymentRequest(
    val requestId: String,
    val qualifier: String,
    val price: Int,
    val card: Card,
    ) {
    fun toDomain(): Payment {
        if (requestId.isBlank()) throw IllegalArgumentException("requestId is blank")
        if (qualifier.isBlank()) throw IllegalArgumentException("qualifier is blank")
        if (price <= 0) throw IllegalArgumentException("price is not positive")
        return Payment.create(requestId, qualifier, card, price)
    }
}