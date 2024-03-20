package com.quid.unit.payments.gateway.out.repository

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.mongoDB.MongoRepository
import com.quid.unit.payments.gateway.out.repository.mongoDB.PaymentDocument
import org.springframework.stereotype.Repository

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun findByRequestId(requestId: String): Payment
    fun checkPaymentNotExists(requestId: String): Boolean


    @Repository
    class PaymentRepositoryImpl(
        private val mongoRepository: MongoRepository
    ) : PaymentRepository {
        override fun save(payment: Payment): Payment = mongoRepository.save(PaymentDocument(payment)).toDomain()

        override fun findByRequestId(requestId: String): Payment =
            mongoRepository.findByRequestId(requestId)?.toDomain()
                ?: throw IllegalAccessException("Payment not found for requestId: $requestId")

        override fun checkPaymentNotExists(requestId: String): Boolean =
            mongoRepository.findByRequestId(requestId) == null
    }
}