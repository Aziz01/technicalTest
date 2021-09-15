package com.info.microdetails.exception;

public class ServiceInfoException extends Exception{

    private static final long serialVersionUID = 1;

    public ServiceInfoException(String message) {
        super(message);
    }

    public ServiceInfoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
