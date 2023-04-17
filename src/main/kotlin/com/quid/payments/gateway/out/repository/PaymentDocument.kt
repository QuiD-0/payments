package com.quid.payments.gateway.out.repository

import com.quid.payments.domain.Card
import com.quid.payments.domain.PayStatus
import com.quid.payments.domain.Payment
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "payments")
class PaymentDocument(
    @Id private val id: ObjectId,
    @Indexed(unique = true) private val requestId: String,
    private val identifier: String,
    private val cardNumber: String,
    private val expiryDate: LocalDate,
    private val cvc: String,
    private val cardHolderName: String,
    private val price: Int,
    private val payStatus: PayStatus,
    private val createdAt: LocalDateTime,
) {
    fun toDomain(): Payment {
        return Payment.of(
            id.toHexString(),
            requestId,
            identifier,
            Card.of(cardNumber, expiryDate, cvc, cardHolderName),
            price,
            payStatus,
            createdAt,
        )
    }

    companion object {
        fun of(payment: Payment): PaymentDocument {
            return PaymentDocument(
                payment.paymentId?.let { ObjectId(it) } ?: ObjectId.get(),
                payment.requestId,
                payment.identifier,
                payment.card.number,
                payment.card.expireDate,
                payment.card.cvc,
                payment.card.holderName,
                payment.price,
                payment.payStatus,
                payment.createdAt,
            )
        }
    }
}