package com.quid.unit.payments.gateway.out.repository

import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.mongoDB.MongoRepository
import org.springframework.stereotype.Repository

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun findByRequestId(paymentId: String): Payment
    fun checkPaymentNotExists(requestId: String): Boolean


    @Repository
    class PaymentRepositoryImpl(
        private val mongoRepository: MongoRepository
    ) : PaymentRepository {
        override fun save(payment: Payment): Payment = mongoRepository.save(PaymentDocument(payment)).toDomain()

        override fun findByRequestId(paymentId: String): Payment =
            mongoRepository.findByRequestId(paymentId)?.toDomain()
                ?: throw IllegalAccessException("Payment not found for requestId: $paymentId")

        override fun checkPaymentNotExists(requestId: String): Boolean =
            mongoRepository.findByRequestId(requestId) == null
    }
}