package com.quid.unit.payments.domain

import java.time.LocalDate

data class Card (
    val number: String,
    val expireDate: LocalDate,
    val cvc: String,
    val holderName: String,
){
    init {
        require(number.isNotBlank()) { "number is blank" }
        require(expireDate.isAfter(LocalDate.now())) { "expireDate is not valid" }
        require(cvc.isNotBlank()) { "cvc is blank" }
    }
}