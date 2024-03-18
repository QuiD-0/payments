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
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    init {
        require(requestId.isNotBlank()) { "requestId is blank" }
        require(identifier.isNotBlank()) { "identifier is blank" }
        require(price > 0) { "price is not positive" }
    }

    fun cancel(): Payment = copy(payStatus = PayStatus.PAYMENT_CANCELED, updatedAt = LocalDateTime.now())
    fun pay(): Payment = copy(payStatus = PayStatus.PAYMENT_COMPLETED, updatedAt = LocalDateTime.now())


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Payment

        if (paymentId != other.paymentId) return false
        if (requestId != other.requestId) return false
        if (price != other.price) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = paymentId?.hashCode() ?: 0
        result = 31 * result + requestId.hashCode()
        result = 31 * result + price
        result = 31 * result + createdAt.hashCode()
        return result
    }
}