package com.zopa;

import com.zopa.service.lender.Lender;
import com.zopa.service.loan.Loan;
import com.zopa.service.payments.Payments;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class LoanBuilder {
    private Lender lender;
    private Double originalRate;
    private Integer loanAmount;
    private Payments payments;

    public LoanBuilder withLender(final Lender lender) {
        this.lender = lender;
        return this;
    }

    public LoanBuilder withOriginalRate(final Double originalRate) {
        this.originalRate = originalRate;
        return this;
    }

    public LoanBuilder withLoanAmount(final Integer loanAmount) {
        this.loanAmount = loanAmount;
        return this;
    }

    public LoanBuilder withPayments(final Payments payments) {
        this.payments = payments;
        return this;
    }

    public Loan build() {
        return new Loan(this.lender, this.originalRate, this.loanAmount, this.payments);
    }
}
