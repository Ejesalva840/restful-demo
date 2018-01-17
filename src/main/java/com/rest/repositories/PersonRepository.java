package com.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rest.entities.Person;

@RepositoryRestResource(path="/person")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	
}
