package com.conference.exceptions;

public class EventServiceException extends Exception {
    public EventServiceException() {
        super();
    }
    public EventServiceException(String message) {
        super(message);
    }
}
