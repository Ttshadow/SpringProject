package com.example.employeeapidemo_qianqian.service;

import com.example.employeeapidemo_qianqian.exception.EmployeeNotFoundException;
import com.example.employeeapidemo_qianqian.model.Employee;
import com.example.employeeapidemo_qianqian.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository repo;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createOrUpdateEmployee(Employee employee){
        return repo.save(employee);
    }

    public Employee findById(long id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = repo.findById(id);
        if (!employeeOptional.isPresent()){
            throw new EmployeeNotFoundException("Employee with id " + id + " is not found.");
        }
        return employeeOptional.get();
    }

    public Collection<Employee> findByFirstNameOrLastName(String name){
        return repo.findByFirstNameOrLastName(name);
    }

    public void deleteEmployee(Employee employee){
        repo.delete(employee);
    }

    public Collection<Employee> findAll(){
        return repo.findAll();
    }
}
