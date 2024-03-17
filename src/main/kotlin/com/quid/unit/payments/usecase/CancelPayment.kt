package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface CancelPayment {
    operator fun invoke(paymentId: String): Payment

    @Service
    class PaymentCancelImpl(
        private val paymentRepository: PaymentRepository
    ) : CancelPayment {

        override fun invoke(paymentId: String): Payment = paymentRepository.cancelPayment(paymentId)
    }
    
}