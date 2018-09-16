package com.zopa.service;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class RateCalculatorException extends Exception {
    public RateCalculatorException() {
        super();
    }

    public RateCalculatorException(final String message) {
        super(message);
    }

    public RateCalculatorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RateCalculatorException(final Throwable cause) {
        super(cause);
    }
}
