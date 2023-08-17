package com.example.employeeapidemo_qianqian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeApiDemoQianqianApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApiDemoQianqianApplication.class, args);
    }

}
