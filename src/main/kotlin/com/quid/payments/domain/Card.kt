package com.quid.payments.domain

import java.time.LocalDate

class Card (
    val number: String,
    val expireDate: LocalDate,
    val cvc: String,
    val holderName: String,
){
    companion object {
        fun of(
            number: String,
            expireDate: LocalDate,
            cvc: String,
            holderName: String
        ): Card {
            return Card(number, expireDate, cvc, holderName)
        }
    }
}