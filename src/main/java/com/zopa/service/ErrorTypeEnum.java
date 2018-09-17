package com.zopa.service;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
public enum ErrorTypeEnum {
    FILE_NOT_FOUND("File not found!"),
    FILE_FORMAT_INVALID("Filename format is invalid!"),
    FILE_NAME_IS_MISSING("Filename is missing!"),
    BORROWING_AMOUNT_OUT_OF_RANGE("Requested amount is not within range! (1000 - 15000)"),
    BORROWING_AMOUNT_INVALID("Requested amount format is invalid!"),
    BORROWING_AMOUNT_NOT_MULTIPLE_OF_100("Requested amount should be a multiple of 100!"),
    LENDER_NOT_AVAILABLE("No lender available!");

    private String error;

    ErrorTypeEnum(final String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public void setError(final String error) {
        this.error = error;
    }
}
