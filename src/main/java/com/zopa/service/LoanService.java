package com.zopa.service;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public interface LoanService {

    Loan getLoan(String fileName, Integer borrowingAmount) throws RateCalculatorException;
}
