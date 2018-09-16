package com.zopa.service;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class Loan {
    private Lender lender;
    private Double originalRate;
    private Integer loanedAmount;
    private Payments payments;

    public Loan(final Lender lender, final Double originalRate, final Integer loanedAmount, final Payments payments) {
        this.lender = lender;
        this.originalRate = originalRate;
        this.loanedAmount = loanedAmount;
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

    public Integer getLoanedAmount() {
        return this.loanedAmount;
    }

    public void setLoanedAmount(final Integer loanedAmount) {
        this.loanedAmount = loanedAmount;
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
                ", loanedAmount=" + this.loanedAmount +
                ", payments=" + this.payments +
                '}';
    }
}
