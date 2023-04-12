package com.quid.payments.gateway.`in`.http

import com.quid.payments.gateway.`in`.http.model.PaymentRequest
import com.quid.payments.gateway.`in`.http.model.PaymentResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class PaymentController {

    @PostMapping
    fun paymentRequest(@RequestBody paymentRequest: PaymentRequest) : PaymentResponse {
        return PaymentResponse("123")
    }
}