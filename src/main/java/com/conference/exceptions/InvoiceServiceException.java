package com.conference.exceptions;

public class InvoiceServiceException extends Exception {

    public InvoiceServiceException() {
        super();
    }

    public InvoiceServiceException(String message) {
        super(message);
    }
}
