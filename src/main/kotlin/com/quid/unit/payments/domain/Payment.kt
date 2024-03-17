package com.quid.unit.payments.domain

import java.time.LocalDateTime

data class Payment(
    val paymentId: String? = null,
    val requestId: String,
    val identifier: String,
    val card: Card,
    val price: Int,
    val payStatus: PayStatus = PayStatus.PAYMENT_WAITING,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(requestId.isNotBlank()) { "requestId is blank" }
        require(identifier.isNotBlank()) { "identifier is blank" }
        require(price > 0) { "price is not positive" }
    }

    fun cancel(): Payment = copy(payStatus = PayStatus.PAYMENT_CANCELED)
    fun pay(): Payment = copy(payStatus = PayStatus.PAYMENT_COMPLETED)

}