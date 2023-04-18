package com.quid.unit.payments.gateway.out.repository.mongoDB

import com.quid.unit.payments.gateway.out.repository.PaymentDocument
import org.springframework.data.repository.CrudRepository

interface MongoRepository : CrudRepository<PaymentDocument, String> {
    fun findByRequestId(paymentId: String): PaymentDocument?
}