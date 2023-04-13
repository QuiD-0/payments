package com.quid.payments.domain

import java.time.LocalDate

class Card (
    val cardNumber: String,
    val expiryDate: LocalDate,
    val cvc: String,
    val cardHolderName: String,
){
    companion object {
        fun of(
            cardNumber: String,
            expiryDate: LocalDate,
            cvc: String,
            cardHolderName: String
        ): Card {
            return Card(cardNumber, expiryDate, cvc, cardHolderName)
        }
    }
}