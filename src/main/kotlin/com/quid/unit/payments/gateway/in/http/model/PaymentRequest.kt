package com.quid.unit.payments.gateway.`in`.http.model

import com.quid.unit.payments.domain.Card
import com.quid.unit.payments.domain.Payment

data class PaymentRequest(
    val requestId: String,
    val identifier: String,
    val price: Int,
    val card: Card,
    ) {
    fun toDomain(): Payment = Payment(null, requestId, identifier, card, price)
}