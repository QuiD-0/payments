package com.quid.payments.gateway.`in`.http.model

import com.quid.payments.domain.Payment
import java.time.LocalDateTime

data class PaymentResponse(val requestId :String, val createdAt: LocalDateTime, val paymentId: String){
    companion object {
        fun of(payment: Payment): PaymentResponse {
            return PaymentResponse(payment.requestId, payment.createdAt, payment.paymentId?: "")
        }
    }

}
