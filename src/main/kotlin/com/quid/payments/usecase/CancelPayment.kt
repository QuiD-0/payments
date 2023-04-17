package com.quid.payments.usecase

import com.quid.payments.domain.Payment
import com.quid.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CancelPayment {
    fun cancel(paymentId: String): Payment

    @Service
    @Transactional
    class PaymentCancelImpl(
        private val paymentRepository: PaymentRepository
    ) : CancelPayment {

        override fun cancel(paymentId: String): Payment = paymentRepository.cancelPayment(paymentId)
    }
    
}