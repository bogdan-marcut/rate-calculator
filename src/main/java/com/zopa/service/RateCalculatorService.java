package com.zopa.service;

import java.util.List;

import static java.util.Comparator.comparingDouble;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class RateCalculatorService {
    public Lender getLenderByBestInterest(final List<Lender> lenders, final Integer borrowingAmount) {
        return lenders.stream()
                .filter(lender -> lender.getMaximumAmount() >= borrowingAmount)
                .min(comparingDouble(Lender::getRate)).orElseThrow(NoLenderAvailable::new);
    }
}
