package com.quid.unit.payments.stub.repository

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository

class StubPaymentRepository: PaymentRepository {
    val payments = mutableListOf<Payment>()

    override fun save(payment: Payment): Payment {
        payments.add(payment)
        return payment
    }

    override fun findByRequestId(paymentId: String): Payment {
        return payments.find { it.requestId == paymentId } ?: throw RuntimeException("Payment not found")
    }

    override fun checkPaymentNotExists(requestId: String): Boolean {
        return payments.none { it.requestId == requestId }
    }
}