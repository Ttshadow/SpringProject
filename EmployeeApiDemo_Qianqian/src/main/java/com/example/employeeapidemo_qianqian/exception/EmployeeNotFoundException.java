package com.example.employeeapidemo_qianqian.exception;

public class EmployeeNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
