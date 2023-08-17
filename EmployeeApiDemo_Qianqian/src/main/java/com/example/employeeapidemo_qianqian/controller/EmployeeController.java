package com.example.employeeapidemo_qianqian.controller;

import com.example.employeeapidemo_qianqian.exception.EmployeeNotFoundException;
import com.example.employeeapidemo_qianqian.model.Employee;
import com.example.employeeapidemo_qianqian.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
        init();
    }

    public void init() {
        Employee e1 = new Employee("Alpha", "Alpha_last", BigDecimal.valueOf(500), "Quebec", "Canada");
        Employee e2 = new Employee("Beta", "Beta_last", BigDecimal.valueOf(400), "Ontario", "Canada");
        Employee e3 = new Employee("Gamma", "Gamma_last", BigDecimal.valueOf(300), "Quebec", "Canada");
        service.createOrUpdateEmployee(e1);
        service.createOrUpdateEmployee(e2);
        service.createOrUpdateEmployee(e3);
    }

    @Operation(summary = "Get All Employees")
    @GetMapping
    public ResponseEntity<Collection<Employee>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Create New Employee")
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.createOrUpdateEmployee(employee));
    }

    @Operation(summary = "Find Employee By Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
    }, headers = {@Header(name = "location")})})
    @GetMapping("/findById/{id}")
    public ResponseEntity<Employee> findById(@PathVariable long id){
       try {
           Employee employee = service.findById(id);
           return ResponseEntity.ok(employee);
       } catch (EmployeeNotFoundException e) {
           e.getMessage();
       }
        return null;
    }

    @Operation(summary = "Find Employee By First Name or Last Name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE)
    }, headers = {@Header(name = "location")})})
    @GetMapping("/findByName")
    public ResponseEntity<Collection<Employee>> findByFistNameOrLastName(@Valid @RequestParam String name) throws EmployeeNotFoundException {
        Collection<Employee> employee = service.findByFirstNameOrLastName(name);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        throw new EmployeeNotFoundException("Employee not Found");
    }

    @Operation(summary = "Update Employee")
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.createOrUpdateEmployee(employee));
    }

    @Operation(summary = "Delete Employee By ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        Employee employee = service.findById(id);
        if (employee != null) {
            service.deleteEmployee(employee);
            return ResponseEntity.ok("Employee (id= " + id + ") has been deleted.");
        }
        throw new EmployeeNotFoundException("Employee not found");
    }
}
