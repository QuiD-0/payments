package com.quid.unit.payments.usecase

import com.quid.unit.payments.domain.Card
import com.quid.unit.payments.domain.PayStatus
import com.quid.unit.payments.domain.Payment
import com.quid.unit.payments.gateway.out.repository.PaymentRepository
import com.quid.unit.payments.stub.repository.StubPaymentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CompletePaymentTest {

    private val paymentRepository: PaymentRepository = StubPaymentRepository()
    private val completePayment = CompletePayment.CompletePaymentUseCase(paymentRepository)

    @BeforeEach
    fun setup() {
        val payment = createPayment(100)
        paymentRepository.save(payment)
    }

    @Test
    @DisplayName("결제 완료")
    fun complete_payment_success() {
        val payment = createPayment(100)

        val result = completePayment(payment)

        assertEquals(result.payStatus, PayStatus.PAYMENT_COMPLETED)
    }

    @Test
    @DisplayName("결제 실패 - 데이터 불일치")
    fun complete_payment_fail_on_data_mismatch() {
        val payment = createPayment(1000)

        assertThrows<IllegalAccessException> { completePayment(payment) }
    }

    private fun createPayment(price: Int): Payment {
        return Payment(
            paymentId = "testId",
            requestId = "123",
            identifier = "123",
            card = Card("123", LocalDate.now().plusDays(1), "123", "testUser"),
            price = price,
        )
    }
}