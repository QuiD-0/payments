package com.quid.payments.gateway.out.repository.mongoDB

import com.quid.payments.gateway.out.repository.PaymentDocument
import org.springframework.data.repository.CrudRepository

interface MongoRepository : CrudRepository<PaymentDocument, String> {
    fun findByRequestId(paymentId: String): PaymentDocument?
}