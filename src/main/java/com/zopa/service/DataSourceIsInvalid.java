package com.zopa.service;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
public class DataSourceIsInvalid extends RuntimeException {
    public DataSourceIsInvalid() {
        super();
    }

    public DataSourceIsInvalid(final String message) {
        super(message);
    }

    public DataSourceIsInvalid(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataSourceIsInvalid(final Throwable cause) {
        super(cause);
    }
}
