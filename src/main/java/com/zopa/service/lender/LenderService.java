package com.zopa.service.lender;

import com.zopa.service.DataSourceIsInvalid;
import com.zopa.service.DataSourceNotAvailable;
import com.zopa.service.RateCalculatorException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import static com.zopa.service.ErrorTypeEnum.LENDER_NOT_AVAILABLE;
import static java.util.Comparator.comparingDouble;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
@Dependent
public class LenderService {

    @Inject
    private LenderRepository lenderRepository;

    public List<Lender> getLenders(final String fileName) throws RateCalculatorException {
        try {
            return this.lenderRepository.getLenders(fileName);
        } catch (final DataSourceNotAvailable | DataSourceIsInvalid e) {
            throw new RateCalculatorException(e.getMessage());
        }
    }

    public Lender getLenderByBestInterest(final String fileName, final Integer borrowingAmount) throws RateCalculatorException {
        final List<Lender> lenders = this.getLenders(fileName);

        return lenders.stream()
                .filter(lender -> lender.getMaximumAmount() >= borrowingAmount)
                .min(comparingDouble(Lender::getRate)).orElseThrow(() -> new RateCalculatorException(LENDER_NOT_AVAILABLE.getError()));
    }
}
