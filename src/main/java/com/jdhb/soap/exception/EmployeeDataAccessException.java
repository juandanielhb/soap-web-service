package com.jdhb.soap.exception;

public class EmployeeDataAccessException extends RuntimeException {

    public EmployeeDataAccessException(String message) {
        super(message);
    }

    public EmployeeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
