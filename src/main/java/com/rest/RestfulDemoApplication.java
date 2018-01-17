package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RestfulDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulDemoApplication.class, args);
	}
}
