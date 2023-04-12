package com.quid.payments.domain

import java.time.LocalDateTime

class Payment(
    val paymentId: Long,
    val qualifier: String,
    val card: Card,
    val price: Int,
    val status: PayStatus,
    val createdAt: LocalDateTime,
) {
}