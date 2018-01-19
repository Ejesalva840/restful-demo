package com.rest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rest.entities.Person;
import com.rest.repositories.PersonRepository;

@SpringBootApplication
public class RestfulDemoApplication {

	/*
	 * Used to seed the data base.
	 * 
	 * This method can be used or you can uncomment the lines in app.properties file 
	 * and use the sql scripts directly
	 */
	@Bean
	protected InitializingBean seedDatabase(PersonRepository repository) {
		return () -> {
			repository.save(new Person("Joe", "Jackson"));
			repository.save(new Person("Hunter", "Fisher"));
			repository.save(new Person("Sally", "Sue"));
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulDemoApplication.class, args);
	}
}
