package com.rest.integration;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.rest.entities.Person;

/**
 * Integration testing for the Person endpoint
 * 
 * <p>
 * Used to check and ensure that the mappings are correct
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PersonIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	/**	
	 * Test to ensure that GET mapping with no ID should return a collection
	 * 
	 * @throws RestClientException, Generic URI exception
	 * @throws URISyntaxException, throws exception if URI is improper
	 */
	@Test
	public void givenPersonEndpoint_whenCallAll_shouldReturnListOfAll() throws RestClientException, URISyntaxException {

		Collection<Person> people = template.exchange(
					new URI(ENDPOINT)
					, HttpMethod.GET
					, HttpEntity.EMPTY
					, new ParameterizedTypeReference<PagedResources<Person>>() {}
				).getBody().getContent();

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(people).isNotNull();
		softly.assertThat(people.size()).isEqualTo(3);
		
		Person person = new ArrayList<Person>(people).get(0);
		
		softly.assertThat(person.getLastName()).isEqualTo("Jackson");
		softly.assertThat(person.getId()).isEqualTo(0);
		
		softly.assertAll();
		
	}
	
	/**
	 * Test for GET Mapping with ID
	 */
	@Test
	public void givenPersonEndPoint_whenCallWithID_shouldGivePerson() {
		Person person = template.getForEntity(ENDPOINT + "/1", Person.class).getBody();
		
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(person.getFirstName()).isEqualTo("Joe");
		softly.assertThat(person.getLastName()).isEqualTo("Jackson");
		softly.assertThat(person.getId()).isEqualTo(0);
		
		softly.assertAll();
		
	}
	

	private static final String ENDPOINT = "/people";

}
