package com.quid.payments.gateway.`in`.http

import com.quid.payments.domain.Payment
import com.quid.payments.gateway.`in`.http.model.PaymentCancelRequest
import com.quid.payments.gateway.`in`.http.model.PaymentRequest
import com.quid.payments.gateway.`in`.http.model.PaymentResponse
import com.quid.payments.usecase.CancelPayment
import com.quid.payments.usecase.CreatePayment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val payment: CreatePayment,
    private val cancelPayment: CancelPayment
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun paymentRequest(@RequestBody request: PaymentRequest) : PaymentResponse {
        val payment : Payment = payment.create(request.toDomain())
        return PaymentResponse.of(payment)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun cancelRequest(@RequestBody request: PaymentCancelRequest) : PaymentResponse {
        val payment : Payment = cancelPayment.cancel(request.paymentId)
        return PaymentResponse.of(payment)
    }
}