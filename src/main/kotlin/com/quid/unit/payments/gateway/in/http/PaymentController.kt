package com.quid.unit.payments.gateway.`in`.http

import com.quid.unit.payments.gateway.`in`.http.model.PaymentCancelRequest
import com.quid.unit.payments.gateway.`in`.http.model.PaymentRequest
import com.quid.unit.payments.gateway.`in`.http.model.PaymentResponse
import com.quid.unit.payments.usecase.CancelPayment
import com.quid.unit.payments.usecase.CreatePayment
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val payment: CreatePayment,
    private val cancelPayment: CancelPayment
) {
    private val logger = LoggerFactory.getLogger(PaymentController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun paymentRequest(@RequestBody request: PaymentRequest) : PaymentResponse {
        logger.info("Payment request received: $request")
        return payment(request.toDomain())
            .let { PaymentResponse.of(it) }
    }

    @PostMapping("/cancel")
    @ResponseStatus(HttpStatus.OK)
    fun cancelRequest(@RequestBody request: PaymentCancelRequest) : PaymentResponse {
        logger.info("Payment cancel request received: $request")
        return cancelPayment(request.paymentId)
            .let { PaymentResponse.of(it) }
    }
}