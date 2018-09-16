package com.zopa;

import com.zopa.service.MonthlyPayment;
import com.zopa.service.Payments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class PaymentsBuilder {
    private final List<MonthlyPayment> monthlyPayments = new ArrayList<>();

    public PaymentsBuilder withMonthlyPayment(final MonthlyPayment monthlyPayment) {
        this.monthlyPayments.add(monthlyPayment);
        return this;
    }

    public Payments build() {
        return new Payments(this.monthlyPayments);
    }
}
