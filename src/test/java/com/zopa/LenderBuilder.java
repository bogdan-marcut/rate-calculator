package com.zopa;

import com.zopa.service.Lender;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class LenderBuilder {
    private String name;
    private Double rate;
    private Double maximumAmount;

    public LenderBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public LenderBuilder withRate(final Double rate) {
        this.rate = rate;
        return this;
    }

    public LenderBuilder withMaximumAmount(final Double maximumAmount) {
        this.maximumAmount = maximumAmount;
        return this;
    }

    public Lender build() {
        return new Lender(this.name, this.rate, this.maximumAmount);
    }
}
