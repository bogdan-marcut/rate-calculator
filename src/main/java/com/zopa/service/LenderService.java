package com.zopa.service;

import com.zopa.repository.LenderRepository;

import javax.inject.Inject;
import java.util.List;

import static java.util.Comparator.comparingDouble;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class LenderService {

    @Inject
    private LenderRepository lenderRepository;

    public List<Lender> getLenders(final String fileName) throws RateCalculatorException {
        try {
            return this.lenderRepository.getLenders(fileName);
        } catch (final DataSourceNotAvailable e) {
            throw new RateCalculatorException("Could not extract data from file " + fileName);
        }
    }

    public Lender getLenderByBestInterest(final String fileName, final Integer borrowingAmount) throws RateCalculatorException {
        final List<Lender> lenders = this.getLenders(fileName);

        return lenders.stream()
                .filter(lender -> lender.getMaximumAmount() >= borrowingAmount)
                .min(comparingDouble(Lender::getRate)).orElseThrow(NoLenderAvailable::new);
    }
}
