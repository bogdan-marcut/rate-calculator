package com.zopa.service;

import com.zopa.LenderBuilder;
import com.zopa.service.lender.Lender;
import com.zopa.service.payments.PaymentService;
import com.zopa.service.payments.Payments;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceShould {

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void
    return_payments() throws Exception {
        final Lender bestLender = new LenderBuilder()
                .withName("Bob")
                .withMaximumAmount(1000.0)
                .withRate(0.07)
                .build();
        final Integer borrowingAmount = 1000;

        final Payments payments = this.paymentService.calculatePayments(bestLender, borrowingAmount);

        assertThat(payments.getMonthlyPayments()).hasSize(36);
        assertThat(payments.getFirstMonthRepayment()).isCloseTo(30.0, Offset.offset(1.0));
        assertThat(payments.getTotalPayment()).isCloseTo(1108.0, Offset.offset(1.0));
    }
}
