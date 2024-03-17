package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface CreatePayment {
    operator fun invoke(payment: Payment): Payment

    @Service
    class PaymentCreateImpl(
        private val paymentRepository: PaymentRepository
    ) : CreatePayment {

        override fun invoke(payment: Payment): Payment =
            paymentRepository.checkPaymentNotExists(payment.requestId)
                .let { paymentRepository.save(payment) }
    }

}