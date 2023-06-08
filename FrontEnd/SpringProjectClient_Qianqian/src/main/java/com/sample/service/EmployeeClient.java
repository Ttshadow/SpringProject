package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sample.exception.EmployeeNotFound;
import com.sample.model.Employee;

import reactor.core.publisher.Mono;

@Component
public class EmployeeClient implements EmployeeClientRest {
	private WebClient client;

	public EmployeeClient() {
		super();
	}

	@Autowired
	public EmployeeClient(WebClient client) {
		super();
		this.client = client;
	}

	@Override
	public List<Employee> findAll() {
		return client.get().retrieve().bodyToFlux(Employee.class).collectList().block();
	}

	@Override
	public Employee findById(Long id) {
		return client.get().uri("/" + id).retrieve()
				.onStatus(s -> s.value() == HttpStatus.NOT_FOUND.value(),
						r -> Mono.error(new EmployeeNotFound("Employee ID " + id + " not found.")))
				.bodyToMono(Employee.class).block();
	}

	@Override
	public Employee save(Employee employee) {
		return client.post().bodyValue(employee).retrieve().bodyToMono(Employee.class).block();
	}

	@Override
	public Employee update(Long id, Employee employee) {
		return client.put().uri("/" + id).bodyValue(employee).retrieve().bodyToMono(Employee.class).block();
	}

	@Override
	public void deleteById(Long id) {
		client.delete().uri("/" + id).retrieve().toBodilessEntity().block();
	}

	@Override
	public List<Employee> findNumberByFirstNameAndLastName(String firstName, String lastName) {
		return client.get().retrieve().bodyToFlux(Employee.class)
				.filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName)).collectList()
				.block();
	}

	@Override
	public List<Employee> checkExistById(Long id) {
		return client.get().retrieve().bodyToFlux(Employee.class)
				.filter(x -> x.getId() == id).collectList().block();
	}

	@Override
	public List<Employee> searchByName(String name) {
		return client.get().retrieve().bodyToFlux(Employee.class)
				.filter(x -> x.getFirstName().toUpperCase().contains(name.toUpperCase()) || x.getLastName().toUpperCase().contains(name.toUpperCase())).collectList().block();
	}

}
