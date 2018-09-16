package com.zopa.service;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class MonthlyPayment {
    private Double amount;

    public MonthlyPayment() {
    }

    public MonthlyPayment(final Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MonthlyPayment{" +
                "amount=" + this.amount +
                '}';
    }
}
