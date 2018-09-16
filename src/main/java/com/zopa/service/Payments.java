package com.zopa.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class Payments {
    private List<MonthlyPayment> monthlyPayments;

    public Payments() {
        this.monthlyPayments = new ArrayList<>();
    }

    public void addPayment(final MonthlyPayment monthlyPayment) {
        this.monthlyPayments.add(monthlyPayment);
    }

    public List<MonthlyPayment> getMonthlyPayments() {
        return this.monthlyPayments;
    }

    public void setMonthlyPayments(final List<MonthlyPayment> monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public Double getTotalPayment() {
        return this.monthlyPayments.stream().mapToDouble(MonthlyPayment::getAmount).sum();
    }


    public Double getFirstMonthRepayment() {
        return this.monthlyPayments.get(0).getAmount();
    }

    @Override
    public String toString() {
        return "Payments{" +
                "monthlyPayments=" + this.monthlyPayments +
                '}';
    }
}
