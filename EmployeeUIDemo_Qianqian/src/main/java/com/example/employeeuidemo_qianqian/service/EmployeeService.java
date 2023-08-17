package com.example.employeeuidemo_qianqian.service;

import com.example.employeeuidemo_qianqian.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {
    private EmployeeRestClient employeeClient;

    @Autowired
    public EmployeeService(EmployeeRestClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public Collection<Employee> getAllEmployees(){
        return employeeClient.findAllEmployees();
    }

    public Employee findById(long id){
        return employeeClient.findById(id);
    }

    public Collection<Employee> findByFirstNameOrLastName(String name){
        return employeeClient.findByFirstNameOrLastName(name);
    }

    public Employee createEmployee(Employee employee){
        return employeeClient.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeClient.update(employee);
    }

    public void deleteEmployee(long id){
        employeeClient.deleteById(id);
    }
}
