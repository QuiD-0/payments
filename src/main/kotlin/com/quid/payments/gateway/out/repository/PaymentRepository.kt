package com.quid.payments.gateway.out.repository

import com.quid.payments.domain.Payment
import com.quid.payments.gateway.out.repository.mongoDB.MongoRepository
import org.springframework.stereotype.Repository

interface PaymentRepository {
    fun save(payment: Payment): Payment

    @Repository
    class PaymentRepositoryImpl(private val mongoRepository: MongoRepository) : PaymentRepository {
        override fun save(payment: Payment): Payment {
            val paymentDocument = mongoRepository.findByRequestId(payment.requestId)
                ?: mongoRepository.save(PaymentDocument.of(payment))
            return paymentDocument.toDomain()
        }
    }
}