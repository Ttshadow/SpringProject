package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.Employee;

@Service
public class EmployeeService {
	private EmployeeClientRest employeeClientRest;

	@Autowired
	public EmployeeService(EmployeeClientRest employeeClientRest) {
		super();
		this.employeeClientRest = employeeClientRest;
	}

	public EmployeeService() {
		super();
	}
	
	public List<Employee> findAll(){
		return employeeClientRest.findAll();
	}
	
	public Employee findById(Long id) {
		return employeeClientRest.findById(id);
	}
	
	public Employee save(Employee employee) {
		return employeeClientRest.save(employee);
	}
	
	public Employee update(Long id, Employee employee) {
		return employeeClientRest.update(id, employee);
	}
	
	public void delete(Long id) {
		employeeClientRest.deleteById(id);
	}
	
	public List<Employee> findNumberByEmployeeFirstNameAndLastName(String firstName, String lastName){
		return employeeClientRest.findNumberByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Employee> checkExistById(Long id){
		return employeeClientRest.checkExistById(id);
	}
	
	public List<Employee> findByName(String name){
		return employeeClientRest.searchByName(name);
	}
}
