package com.zopa.controller;

import com.zopa.LenderBuilder;
import com.zopa.LoanBuilder;
import com.zopa.PaymentsBuilder;
import com.zopa.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanControllerShould {

    @InjectMocks
    private final LoanController loanController = new LoanControllerImpl();

    @Mock
    private LoanService loanService;

    @Test
    public void
    return_a_loan() throws Exception {
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        final String fileName = "file";
        final String amount = "1000";
        final Lender lender = new LenderBuilder()
                .withName("Name")
                .withMaximumAmount(10000.0)
                .withRate(0.07)
                .build();
        final Payments payments = new PaymentsBuilder()
                .withMonthlyPayment(new MonthlyPayment(30.0))
                .withMonthlyPayment(new MonthlyPayment(35.0))
                .build();
        final Loan loan = new LoanBuilder()
                .withLender(lender)
                .withOriginalRate(0.07)
                .withLoanAmount(1000)
                .withPayments(payments)
                .build();
        given(this.loanService.calculateBestLoan(anyString(), anyInt())).willReturn(loan);

        this.loanController.calculateRate(new String[]{fileName, amount});

        verify(this.loanService, times(1)).calculateBestLoan(anyString(), anyInt());
        verify(out, times(4)).format(any(), any());
    }

    @Test
    public void
    print_message_when_filename_is_invalid() throws Exception {
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        final String fileName = "";
        final String amount = "1000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        verify(this.loanService, times(0)).calculateBestLoan(anyString(), anyInt());
        verify(out, times(1)).println(anyString());
    }

    @Test
    public void
    print_message_when_amount_is_invalid() throws Exception {
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        final String fileName = "Test";
        final String amount = "";

        this.loanController.calculateRate(new String[]{fileName, amount});

        verify(this.loanService, times(0)).calculateBestLoan(anyString(), anyInt());
        verify(out, times(1)).println(anyString());
    }

    @Test
    public void
    print_message_when_amount_is_not_within_range() throws Exception {
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        final String fileName = "Test";
        final String amount = "100000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        verify(this.loanService, times(0)).calculateBestLoan(anyString(), anyInt());
        verify(out, times(1)).println(anyString());
    }

    @Test
    public void
    print_message_when_amount_is_not_a_multiple_of_100() throws Exception {
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        final String fileName = "Test";
        final String amount = "1001";

        this.loanController.calculateRate(new String[]{fileName, amount});

        verify(this.loanService, times(0)).calculateBestLoan(anyString(), anyInt());
        verify(out, times(1)).println(anyString());
    }
}
