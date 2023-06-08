package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Employee;
import com.sample.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}

	public EmployeeController() {
		super();
	}

	@Operation(summary = "Save Employee")
	@PostMapping
	public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
		return ResponseEntity.ok(service.save(employee));
	}

	@Operation(summary = "Get All Employee")
	@GetMapping
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Operation(summary = "Get Employee By ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			@Content(mediaType = MediaType.APPLICATION_XML_VALUE) }, headers = {
					@Header(name = "location", description = "url to id") }, description = "Get Employee by Id"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Operation(summary = "Update Employee By ID")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
		return ResponseEntity.ok(service.save(employee));
	}

	@Operation(summary = "Delete Employee By ID")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		service.delete(id);
	}

}
