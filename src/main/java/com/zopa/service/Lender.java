package com.zopa.service;

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

    public Lender(String name, Double rate, Double maximumAmount) {
        super();
        this.name = name;
        this.rate = rate;
        this.maximumAmount = maximumAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", maximumAmount=" + maximumAmount +
                '}';
    }
}
