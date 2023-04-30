package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CreatePayment {
    fun create(payment: Payment): Payment

    @Service
    @Transactional
    class PaymentCreateImpl(
        private val paymentRepository: PaymentRepository
    ) : CreatePayment {

        override fun create(payment: Payment): Payment = paymentRepository.save(payment)
            .let { paymentRepository.completePayment(it.requestId) }
    }

}