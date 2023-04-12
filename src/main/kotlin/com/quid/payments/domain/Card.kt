package com.quid.payments.domain

import java.time.LocalDate

class Card (
    val cardNumber: String,
    val expiryDate: LocalDate,
    val cvc: String,
    val cardHolderName: String,
){
}