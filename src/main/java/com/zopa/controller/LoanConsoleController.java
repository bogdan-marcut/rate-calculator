package com.zopa.controller;

import com.zopa.service.RateCalculatorException;
import com.zopa.service.loan.Loan;
import com.zopa.service.loan.LoanService;

import javax.inject.Inject;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class LoanConsoleController implements LoanController {

    @Inject
    private LoanService loanService;

    @Override
    public void calculateRate(final String[] args) {
        try {
            final String fileName = this.validateFileName(args[0]);
            final Integer borrowingAmount = this.validateRequestedAmount(args[1]);

            final Loan loan = this.loanService.calculateBestLoan(fileName, borrowingAmount);

            this.printLoan(loan);
        } catch (final RateCalculatorException e) {
            System.out.println(e.getMessage());
        }
    }


    private String validateFileName(final String fileName) throws RateCalculatorException {
        if (fileName == null) throw new RateCalculatorException("Filename is missing!");
        if (fileName.isEmpty()) throw new RateCalculatorException("Filename format is invalid!");
        return fileName;
    }

    private Integer validateRequestedAmount(final String requestedAmount) throws RateCalculatorException {
        final Integer borrowingAmount = this.parseInt(requestedAmount);

        if (this.validateRequestedAmountRange(borrowingAmount))
            throw new RateCalculatorException("Requested amount is not within range! (1000 - 15000)");
        if (borrowingAmount % 100 != 0)
            throw new RateCalculatorException("Requested amount should be a multiple of 100!");

        return borrowingAmount;
    }

    private Integer parseInt(final String requestedAmount) throws RateCalculatorException {
        try {
            return Integer.parseInt(requestedAmount);
        } catch (final NumberFormatException e) {
            throw new RateCalculatorException("Requested amount format is invalid!");
        }
    }

    private boolean validateRequestedAmountRange(final Integer borrowingAmount) {
        return borrowingAmount < 1000 || borrowingAmount > 15000;
    }

    private void printLoan(final Loan loan) {
        System.out.format("Requested amount: £%d \n", loan.getLoanAmount());
        System.out.format("Rate: %.1f%% \n", loan.getOriginalRate() * 100);
        System.out.format("Monthly repayment: £%.2f \n", loan.getPayments().getFirstMonthRepayment());
        System.out.format("Total repayment: £%.2f \n", loan.getPayments().getTotalPayment());
    }
}
