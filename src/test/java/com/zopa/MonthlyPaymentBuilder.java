package com.zopa;

import com.zopa.service.payments.MonthlyPayment;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class MonthlyPaymentBuilder {
    private Double amount;

    public MonthlyPaymentBuilder withAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public MonthlyPayment build() {
        return new MonthlyPayment(this.amount);
    }
}
