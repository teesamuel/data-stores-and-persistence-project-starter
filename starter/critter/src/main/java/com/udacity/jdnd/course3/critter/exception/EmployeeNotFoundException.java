package com.udacity.jdnd.course3.critter.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
