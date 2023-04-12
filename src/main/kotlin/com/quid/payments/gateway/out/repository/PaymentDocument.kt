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
    private val id: ObjectId,
    private val paymentId: Long,
    private val qualifier: String,
    private val cardNumber: String,
    private val expiryDate: LocalDate,
    private val cvc: String,
    private val cardHolderName: String,
    private val price: Int,
    private val status: PayStatus,
    private val createdAt: LocalDateTime,
){
}