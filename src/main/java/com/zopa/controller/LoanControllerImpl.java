package com.zopa.controller;

import com.zopa.service.Loan;
import com.zopa.service.LoanService;
import com.zopa.service.RateCalculatorException;

import javax.inject.Inject;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class LoanControllerImpl implements LoanController {

    @Inject
    private LoanService loanService;

    @Override
    public void calculateRate(final String[] args) {
        final String fileName = args[0];
        final Integer borrowingAmount = Integer.parseInt(args[1]);

        try {
            final Loan loan = this.loanService.getLoan(fileName, borrowingAmount);
            this.printLoan(loan);
        } catch (final RateCalculatorException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printLoan(final Loan loan) {
        System.out.println("Requested amount: " + loan.getLoanedAmount());
        System.out.println("Rate: " + loan.getOriginalRate() * 100 + "%");
        System.out.println("Monthly repayment: " + loan.getPayments().getFirstMonthRepayment());
        System.out.println("Total repayment: " + loan.getPayments().getTotalPayment());
    }
}
