package com.quid.payments.gateway.out.repository

import com.quid.payments.domain.PayStatus.PAYMENT_CANCELED
import com.quid.payments.domain.PayStatus.PAYMENT_COMPLETED
import com.quid.payments.domain.Payment
import com.quid.payments.gateway.out.repository.mongoDB.MongoRepository
import org.springframework.stereotype.Repository

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun completePayment(paymentId: String)
    fun cancelPayment(paymentId: String): Payment

    @Repository
    class PaymentRepositoryImpl(private val mongoRepository: MongoRepository) : PaymentRepository {
        override fun save(payment: Payment): Payment =
            mongoRepository.findByRequestIdOrNull(payment.requestId)
                ?.let { throw IllegalStateException("Payment already exists") }
                ?: mongoRepository.save(PaymentDocument.of(payment)).toDomain()


        override fun completePayment(paymentId: String): Unit = run {
            mongoRepository.findByRequestIdOrNull(paymentId)
                ?.let { PaymentDocument.of(it.toDomain().copy(PAYMENT_COMPLETED)).toDomain() }
                ?: throw IllegalStateException("Payment not found")
        }

        override fun cancelPayment(paymentId: String): Payment =
            mongoRepository.findByRequestIdOrNull(paymentId)
                ?.let { PaymentDocument.of(it.toDomain().copy(PAYMENT_CANCELED)).toDomain() }
                ?: throw IllegalStateException("Payment not found")

    }
}