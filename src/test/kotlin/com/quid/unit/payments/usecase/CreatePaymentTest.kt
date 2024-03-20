package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Card
import com.quid.unit.payments.domain.PayStatus
import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import com.quid.unit.payments.stub.repository.StubPaymentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CreatePaymentTest{

    private val paymentRepository: PaymentRepository = StubPaymentRepository()
    private val createPayment = CreatePayment.PaymentCreateImpl(paymentRepository)

    @Test
    @DisplayName("결제 생성")
    fun create_payment_success(){
        val payment = Payment(
            paymentId = "testId",
            requestId = "123",
            identifier = "123",
            card = Card("123", LocalDate.now().plusDays(1), "123", "testUser"),
            price = 100,
        )
        val result = createPayment(payment)
        assertEquals(result.payStatus, PayStatus.PAYMENT_WAITING)
    }

    @Test
    @DisplayName("결제 실패 - 데이터 중복")
    fun create_payment_fail_on_data_duplicate(){
        val payment = Payment(
            paymentId = "testId",
            requestId = "123",
            identifier = "123",
            card = Card("123", LocalDate.now().plusDays(1), "123", "testUser"),
            price = 100,
        )
        paymentRepository.save(payment)
        assertThrows<IllegalAccessException> { createPayment(payment) }
    }
}