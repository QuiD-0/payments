package com.quid.unit.payments.stub.repository

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository

class StubPaymentRepository: PaymentRepository {
    private val payments = mutableListOf<Payment>()

    override fun save(payment: Payment): Payment {
        payments.add(payment)
        return payment
    }

    override fun findByRequestId(requestId: String): Payment {
        return payments.find { it.requestId == requestId } ?: throw IllegalAccessException("Payment not found")
    }

    override fun checkPaymentNotExists(requestId: String): Boolean {
        return payments.none { it.requestId == requestId }
    }
}