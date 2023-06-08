package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringWeek2ProjectClientQianqianApplication {
	public final String URL = "http://localhost:8080/employee";

	public static void main(String[] args) {
		SpringApplication.run(SpringWeek2ProjectClientQianqianApplication.class, args);
	}

	@Bean
	WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
	
	@Bean
	WebClient EmployeeWebClient(WebClient.Builder builder) {
		return builder.baseUrl(URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
