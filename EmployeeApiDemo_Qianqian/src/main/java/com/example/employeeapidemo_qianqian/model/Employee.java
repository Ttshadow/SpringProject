package com.example.employeeapidemo_qianqian.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    private BigDecimal salary;
    private String state;
    private String country;

    public Employee(String firstName, String lastName, BigDecimal salary, String state, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.state = state;
        this.country = country;
    }
}