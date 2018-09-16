package com.zopa.service;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class DataSourceNotAvailable extends Exception {
    public DataSourceNotAvailable() {
        super();
    }

    public DataSourceNotAvailable(final String message) {
        super(message);
    }

    public DataSourceNotAvailable(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataSourceNotAvailable(final Throwable cause) {
        super(cause);
    }
}
