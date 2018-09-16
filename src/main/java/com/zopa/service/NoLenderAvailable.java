package com.zopa.service;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public class NoLenderAvailable extends RuntimeException {
    public NoLenderAvailable() {
        super();
    }

    public NoLenderAvailable(final String message) {
        super(message);
    }

    public NoLenderAvailable(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoLenderAvailable(final Throwable cause) {
        super(cause);
    }
}
