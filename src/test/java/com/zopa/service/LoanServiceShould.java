package com.zopa.service;

import com.zopa.LenderBuilder;
import com.zopa.PaymentsBuilder;
import com.zopa.service.lender.Lender;
import com.zopa.service.lender.LenderService;
import com.zopa.service.loan.Loan;
import com.zopa.service.loan.LoanService;
import com.zopa.service.payments.MonthlyPayment;
import com.zopa.service.payments.PaymentService;
import com.zopa.service.payments.Payments;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanServiceShould {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LenderService lenderService;

    @Mock
    private PaymentService paymentService;

    @Test
    public void
    return_loan() throws Exception {
        final Lender bestLender = new LenderBuilder()
                .withName("Bob")
                .withRate(0.07)
                .withMaximumAmount(100.0)
                .build();
        final Payments payments = new PaymentsBuilder()
                .withMonthlyPayment(new MonthlyPayment(100.0))
                .times(36)
                .build();
        given(this.lenderService.getLenderByBestInterest(any(), anyInt())).willReturn(bestLender);
        given(this.paymentService.calculatePayments(eq(bestLender), anyInt())).willReturn(payments);

        final Loan loan = this.loanService.calculateBestLoan("file.csv", 1000);

        assertThat(loan.getPayments()).isEqualTo(payments);
        assertThat(loan.getLender()).isEqualTo(bestLender);
        assertThat(loan.getLoanAmount()).isEqualTo(1000);
        assertThat(loan.getOriginalRate()).isEqualTo(bestLender.getRate());
    }
}

