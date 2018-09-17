package com.zopa.service.loan;

import com.zopa.service.lender.Lender;
import com.zopa.service.payments.Payments;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class Loan {
    private Lender lender;
    private Double originalRate;
    private Integer loanAmount;
    private Payments payments;

    public Loan() {
    }

    public Loan(final Lender lender, final Double originalRate, final Integer loanAmount, final Payments payments) {
        this.lender = lender;
        this.originalRate = originalRate;
        this.loanAmount = loanAmount;
        this.payments = payments;
    }

    public Lender getLender() {
        return this.lender;
    }

    public void setLender(final Lender lender) {
        this.lender = lender;
    }

    public Double getOriginalRate() {
        return this.originalRate;
    }

    public void setOriginalRate(final Double originalRate) {
        this.originalRate = originalRate;
    }

    public Integer getLoanAmount() {
        return this.loanAmount;
    }

    public void setLoanAmount(final Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Payments getPayments() {
        return this.payments;
    }

    public void setPayments(final Payments payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "lender=" + this.lender +
                ", originalRate=" + this.originalRate +
                ", loanAmount=" + this.loanAmount +
                ", payments=" + this.payments +
                '}';
    }
}
