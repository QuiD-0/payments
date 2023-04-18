package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
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