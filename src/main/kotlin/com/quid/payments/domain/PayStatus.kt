package com.quid.payments.domain

enum class PayStatus {
    PAYMENT_WAITING,
    PAYMENT_COMPLETED,
    PAYMENT_CANCELED,
    PAYMENT_FAILED
}