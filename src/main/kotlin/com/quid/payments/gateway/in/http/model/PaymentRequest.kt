package com.quid.payments.gateway.`in`.http.model

import com.quid.payments.domain.Card

data class PaymentRequest(
    val paymentId: Long,
    val qualifier: String,
    val price: Int,
    val card: Card,
    ) {
}