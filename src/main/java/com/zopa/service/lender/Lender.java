package com.zopa.service.lender;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class Lender {
    private String name;
    private Double rate;
    private Double maximumAmount;

    public Lender() {
        super();
    }

    public Lender(final String name, final Double rate, final Double maximumAmount) {
        super();
        this.name = name;
        this.rate = rate;
        this.maximumAmount = maximumAmount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setRate(final Double rate) {
        this.rate = rate;
    }

    public Double getMaximumAmount() {
        return this.maximumAmount;
    }

    public void setMaximumAmount(final Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + this.name + '\'' +
                ", rate=" + this.rate +
                ", maximumAmount=" + this.maximumAmount +
                '}';
    }
}
