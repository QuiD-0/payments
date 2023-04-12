package com.quid.payments.gateway.out.repository

import com.quid.payments.domain.PayStatus
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "payments")
class PaymentDocument (
    @Id
    val id: ObjectId,
    val paymentId: Long,
    val qualifier: String,
    val cardNumber: String,
    val expiryDate: LocalDate,
    val cvc: String,
    val cardHolderName: String,
    val price: Int,
    val currency: String,
    val status: PayStatus,
    val createdAt: LocalDateTime,
){
}