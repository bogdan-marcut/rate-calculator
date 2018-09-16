package com.zopa.service;

import com.zopa.repository.LenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class LoanServiceImpl implements LoanService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private LenderRepository lenderRepository;

    @Inject
    private RateCalculatorService rateCalculatorService;

    @Inject
    private PaymentService paymentService;

    @Override
    public Loan getLoan(final String fileName, final Integer borrowingAmount) throws RateCalculatorException {
        try {
            final List<Lender> lenders = this.lenderRepository.getLenders(fileName);

            final Lender bestLender = this.rateCalculatorService.getLenderByBestInterest(lenders, borrowingAmount);

            final Payments payments = this.paymentService.calculatePayments(bestLender, borrowingAmount);

            final Loan loan = new Loan(bestLender, bestLender.getRate(), borrowingAmount, payments);

            this.logger.error(bestLender.toString());
            this.logger.error(payments.toString());
            this.logger.error(loan.toString());

            return loan;
        } catch (final DataSourceNotAvailable e) {
            throw new RateCalculatorException(e.getMessage());
        } catch (final NoLenderAvailable e) {
            throw new RateCalculatorException("No lender available!");
        }
    }
}
