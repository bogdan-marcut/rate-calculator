package com.zopa;

import com.zopa.service.payments.MonthlyPayment;
import com.zopa.service.payments.Payments;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.nCopies;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class PaymentsBuilder {
    private MonthlyPayment monthlyPayment;

    private Integer times = 1;

    public PaymentsBuilder withMonthlyPayment(final MonthlyPayment monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
        return this;
    }

    public PaymentsBuilder times(final Integer times) {
        this.times = times;
        return this;
    }

    public Payments build() {
        final List<MonthlyPayment> monthlyPayments = new ArrayList<>(nCopies(this.times, this.monthlyPayment));
        return new Payments(monthlyPayments);
    }
}
