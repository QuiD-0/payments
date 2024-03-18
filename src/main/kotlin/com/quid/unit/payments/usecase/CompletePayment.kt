package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import org.springframework.stereotype.Service

fun interface CompletePayment {
    operator fun invoke(request: Payment): Payment

    @Service
    class CompletePaymentUseCase(
        private val paymentRepository: PaymentRepository
    ) : CompletePayment{
        override fun invoke(request: Payment): Payment {
            val payment = paymentRepository.findByRequestId(request.requestId)
            require(payment.isSame(request)) { "Payment is not same" }

            return payment.pay()
                .let { paymentRepository.save(it) }
        }
    }
}