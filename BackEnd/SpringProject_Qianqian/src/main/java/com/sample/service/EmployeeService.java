package com.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.exception.EmployeeNotFoundException;
import com.sample.model.Employee;
import com.sample.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private EmployeeRepository repo;

	@Autowired
	public EmployeeService(EmployeeRepository repo) {
		super();
		this.repo = repo;
	}

	public EmployeeService() {
		super();
	}
	
	public Employee save(Employee employee) {
		return repo.save(employee);
	}
	
	public List<Employee> findAll(){
		return repo.findAll();
	}
	
	public Employee findById(Long id) {
		Optional<Employee> emOptional = repo.findById(id);
		if(!emOptional.isPresent()) {
			throw new EmployeeNotFoundException("Employee with id " + id + " is not found.");
		}
		return emOptional.get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
