package com.example.employeeuidemo_qianqian.service;

import com.example.employeeuidemo_qianqian.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient("employee-server")
public interface EmployeeRestClient {
    final String EMPLOYEE_API_BASE = "/employee";

    @GetMapping(EMPLOYEE_API_BASE)
    Collection<Employee> findAllEmployees();

    @GetMapping(EMPLOYEE_API_BASE+"/findById/{id}")
    Employee findById(@PathVariable long id);

    @GetMapping(EMPLOYEE_API_BASE + "/findByName")
    Collection<Employee> findByFirstNameOrLastName(@RequestParam String name);

    @PostMapping(EMPLOYEE_API_BASE)
    Employee save(@RequestBody Employee employee);

    @PutMapping(EMPLOYEE_API_BASE)
    Employee update(@RequestBody Employee employee);

    @DeleteMapping(EMPLOYEE_API_BASE+"{id}")
    void deleteById(@PathVariable long id);
}
