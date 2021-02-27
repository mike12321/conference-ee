package com.conference.exceptions;

public class ProductServiceException extends Exception {
    public ProductServiceException() {
        super();
    }
    public ProductServiceException(String message) {
        super(message);
    }
}
