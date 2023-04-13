package com.quid.payments.gateway.out.repository

import com.quid.payments.domain.Card
import com.quid.payments.domain.Payment
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "payments")
class PaymentDocument (
    @Id private val id: ObjectId? = null,
    @Indexed(unique = true) private val requestId: String,
    private val qualifier: String,
    private val cardNumber: String,
    private val expiryDate: LocalDate,
    private val cvc: String,
    private val cardHolderName: String,
    private val price: Int,
    private val createdAt: LocalDateTime,
){
    fun toDomain(): Payment {
        return Payment.of(
            id = id?.toHexString(),
            requestId = requestId,
            qualifier = qualifier,
            card = Card.of(cardNumber, expiryDate, cvc, cardHolderName),
            price = price,
            createdAt = createdAt,
        )
    }

    companion object {
        fun of(payment: Payment): PaymentDocument {
            return PaymentDocument(
                requestId = payment.requestId,
                qualifier = payment.qualifier,
                cardNumber = payment.card.cardNumber,
                expiryDate = payment.card.expiryDate,
                cvc = payment.card.cvc,
                cardHolderName = payment.card.cardHolderName,
                price = payment.price,
                createdAt = payment.createdAt
            )
        }
    }
}