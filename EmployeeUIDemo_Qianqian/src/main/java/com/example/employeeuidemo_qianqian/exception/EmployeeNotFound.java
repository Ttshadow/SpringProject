package com.example.employeeuidemo_qianqian.exception;

public class EmployeeNotFound extends Throwable {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFound(String message) {
        super(message);
    }
}

