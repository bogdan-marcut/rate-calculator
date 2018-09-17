package com.zopa.service.loan;

import com.zopa.service.RateCalculatorException;
import com.zopa.service.lender.Lender;
import com.zopa.service.lender.LenderService;
import com.zopa.service.payments.PaymentService;
import com.zopa.service.payments.Payments;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
@Dependent
public class LoanService {

    @Inject
    private LenderService lenderService;

    @Inject
    private PaymentService paymentService;

    public Loan calculateBestLoan(final String fileName, final Integer borrowingAmount) throws RateCalculatorException {
        final Lender bestLender = this.lenderService.getLenderByBestInterest(fileName, borrowingAmount);

        final Payments payments = this.paymentService.calculatePayments(bestLender, borrowingAmount);

        return new Loan(bestLender, bestLender.getRate(), borrowingAmount, payments);
    }

}
