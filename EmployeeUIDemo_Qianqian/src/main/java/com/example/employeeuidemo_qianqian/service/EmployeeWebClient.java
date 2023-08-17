package com.example.employeeuidemo_qianqian.service;

import com.example.employeeuidemo_qianqian.exception.EmployeeNotFound;
import com.example.employeeuidemo_qianqian.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
public class EmployeeWebClient implements EmployeeRestClient {
    private WebClient webClient;

    @Autowired
    public EmployeeWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return webClient.get().retrieve().bodyToFlux(Employee.class).collectList().block();
    }

    @Override
    public Employee findById(long id) {
        return webClient.get().uri("/findById/" + id).retrieve().onStatus(s -> s.value() == HttpStatus.NOT_FOUND.value(),
                r -> Mono.error(new EmployeeNotFound("Employee ID " + id + " not found."))).bodyToMono(Employee.class).block();
    }

    @Override
    public Collection<Employee> findByFirstNameOrLastName(@RequestParam String name) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/findByName")
                .queryParam("name", name)
                .build())
                .retrieve().bodyToFlux(Employee.class).collectList().block();
    }

    @Override
    public Employee save(Employee employee) {
        return webClient.post().uri("").bodyValue(employee).retrieve().bodyToFlux(Employee.class).blockFirst();
    }

    @Override
    public Employee update(Employee employee) {
        return webClient.put().uri("").bodyValue(employee).retrieve().bodyToFlux(Employee.class).blockFirst();
    }

    @Override
    public void deleteById(@PathVariable long id) {
        webClient.delete().uri("/" + id).retrieve().toBodilessEntity().block();
    }
}
