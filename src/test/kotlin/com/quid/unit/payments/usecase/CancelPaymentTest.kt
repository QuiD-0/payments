package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Card
import com.quid.unit.payments.domain.PayStatus
import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.stub.repository.StubPaymentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CancelPaymentTest {

    private val paymentRepository = StubPaymentRepository()
    private val cancelPayment = CancelPayment.PaymentCancelImpl(paymentRepository)

    @BeforeEach
    fun setUp() {
        val payment = Payment(
            paymentId = "testId",
            requestId = "123",
            identifier = "123",
            card = Card("123", LocalDate.now().plusDays(1), "123", "testUser"),
            price = 100,
        )
        paymentRepository.save(payment)
    }

    @Test
    @DisplayName("결제 취소")
    fun cancel_payment_success() {
        val result = cancelPayment("123")
        assertEquals(result.payStatus, PayStatus.PAYMENT_CANCELED)
    }
    
    @Test
    @DisplayName("결제 실패 - 데이터 검색 실패")
    fun cancel_payment_fail_on_data_search_fail() {
        assertThrows<IllegalAccessException> { cancelPayment("1234") }
    }
}