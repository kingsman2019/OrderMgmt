package com.example.ordermgmt.exception;

/**
 * Base for all application exception
 */
public class BaseException extends RuntimeException{

    /**
     * Default constructor
     */
    public BaseException() {
        super();
    }

    /**
     * Constructor with message param
     * @param message String
     */
    public BaseException(String message) {
        super(message);
    }
}
