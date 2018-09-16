package com.zopa.service;

import javax.inject.Inject;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class LoanService {

    @Inject
    private LenderService lenderService;

    @Inject
    private PaymentService paymentService;

    public Loan calculateBestLoan(final String fileName, final Integer borrowingAmount) throws RateCalculatorException {
        try {

            final Lender bestLender = this.lenderService.getLenderByBestInterest(fileName, borrowingAmount);

            final Payments payments = this.paymentService.calculatePayments(bestLender, borrowingAmount);

            final Loan loan = new Loan(bestLender, bestLender.getRate(), borrowingAmount, payments);

            return loan;
        } catch (final NoLenderAvailable e) {
            throw new RateCalculatorException("No lender available for the selected amount!");
        }
    }

}
