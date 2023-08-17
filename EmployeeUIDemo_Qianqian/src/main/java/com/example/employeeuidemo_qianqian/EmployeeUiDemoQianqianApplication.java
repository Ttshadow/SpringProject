package com.example.employeeuidemo_qianqian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeUiDemoQianqianApplication {
    private final String EMPLOYEE_REST_BASE_URL = "http://employee-server/employee";
    public static void main(String[] args) {
        SpringApplication.run(EmployeeUiDemoQianqianApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder wBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient employeeRestWebClient(WebClient.Builder builder){
        return builder.baseUrl(EMPLOYEE_REST_BASE_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
