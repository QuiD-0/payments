package com.quid.unit.payments.gateway.`in`.http.model

import com.quid.unit.payments.domain.PayStatus
import com.quid.unit.payments.domain.Payment
import java.time.LocalDateTime

data class PaymentResponse(val requestId :String, val createdAt: LocalDateTime, val paymentId: String, val payStatus: PayStatus, val payAmount : Int){
    companion object {
        fun of(payment: Payment): PaymentResponse {
            return PaymentResponse(payment.requestId, payment.createdAt, payment.paymentId!!, payment.payStatus, payment.price)
        }
    }

}
