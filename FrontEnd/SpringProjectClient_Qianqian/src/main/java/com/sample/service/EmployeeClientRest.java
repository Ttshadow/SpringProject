package com.sample.service;

import java.util.List;

import com.sample.model.Employee;

public interface EmployeeClientRest {
	List<Employee> findAll();
	Employee findById(Long id);
	Employee save(Employee employee);
	Employee update(Long id, Employee employee);
	void deleteById(Long id);
	List<Employee> findNumberByFirstNameAndLastName(String firstName, String lastName);
	List<Employee> checkExistById(Long id);
	List<Employee> searchByName(String name);
}
