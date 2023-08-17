package com.example.employeeuidemo_qianqian.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String state;
    private String country;
}
