package com.zopa.service.payments;

import com.zopa.service.lender.Lender;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.pow;
import static java.util.stream.Collectors.*;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
@Dependent
public class PaymentService {

    private static final int LOANS = 36;
    private static final int PERIODS = 12;

    public Payments calculatePayments(final Lender bestLender, final Integer borrowingAmount) {
        final Payments payments = new Payments();

        final List<MonthlyPayment> monthlyPaymentList = IntStream.range(0, LOANS)
                .mapToObj(value -> new MonthlyPayment(this.getRepayment(bestLender, borrowingAmount)))
                .collect(toList());

        payments.setMonthlyPayments(monthlyPaymentList);

        return payments;
    }

    private Double getRepayment(final Lender bestLender, final Integer borrowingAmount) {
        final Double periodicInterestRate = pow((1.0 + bestLender.getRate()), (1.0 / PERIODS)) - 1;
        final Double loanValue = borrowingAmount.doubleValue();

        return ((periodicInterestRate * loanValue) / (1.0 - pow(1.0 + periodicInterestRate, -LOANS)));
    }
}
