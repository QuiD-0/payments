package com.quid.payments.usecase

import com.quid.payments.domain.Payment
import com.quid.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CreatePayment {
    fun create(payment: Payment): Payment

    @Service
    @Transactional
    class PaymentCreateImpl(
        private val paymentRepository: PaymentRepository
    ) : CreatePayment {

        override fun create(payment: Payment): Payment {
            val savedPayment = paymentRepository.save(payment)
            paymentRepository.completePayment(savedPayment.paymentId!!)
            return savedPayment
        }
    }

}