package com.quid.unit.payments.gateway.out.repository

import com.quid.unit.payments.domain.PayStatus.PAYMENT_CANCELED
import com.quid.unit.payments.domain.PayStatus.PAYMENT_COMPLETED
import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.mongoDB.MongoRepository
import org.springframework.stereotype.Repository

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun completePayment(paymentId: String)
    fun cancelPayment(paymentId: String): Payment

    @Repository
    class PaymentRepositoryImpl(private val mongoRepository: MongoRepository) : PaymentRepository {
        override fun save(payment: Payment): Payment =
            mongoRepository.findByRequestId(payment.requestId)
                ?.let { throw IllegalStateException("Payment already exists") }
                ?: mongoRepository.save(PaymentDocument.of(payment)).toDomain()


        override fun completePayment(paymentId: String): Unit = run {
            mongoRepository.findByRequestId(paymentId)
                ?.let { PaymentDocument.of(it.toDomain().copy(PAYMENT_COMPLETED)) }
                ?.let { mongoRepository.save(it) }
                ?: throw IllegalStateException("Payment not found")
        }

        override fun cancelPayment(paymentId: String): Payment =
            mongoRepository.findByRequestId(paymentId)
                ?.let { PaymentDocument.of(it.toDomain().copy(PAYMENT_CANCELED)) }
                ?.let { mongoRepository.save(it).toDomain() }
                ?: throw IllegalStateException("Payment not found")

    }
}