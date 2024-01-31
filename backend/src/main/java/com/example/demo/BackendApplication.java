package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.springframework.data.annotation.Id;


class Customer {

  @Id
  public String id;

  public String firstName;
  public String lastName;

  public Customer() {}

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%s, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}

interface ICustomerRepository extends MongoRepository<Customer, String>
{
	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
}
@SpringBootApplication
@RestController
public class BackendApplication  {

	public static void main(String[] args) {

		String mongoDbUrl = "mongodb+srv://atlasMastery:<password>@cluster0.3hayai4.mongodb.net/?retryWrites=true&w=majority";
		try (MongoClient mongoClient = MongoClients.create(mongoDbUrl))
		{
			// MongoDatabase database = mongoClient.getDatabase("")
		} catch (Exception e) {
		}


		SpringApplication.run(BackendApplication.class, args);
	}

	@GetMapping("/hello")
    public String hello(
		@RequestParam(value = "name", defaultValue = "World") String name
	) {
      return String.format("Hello %s!", name);
    }

}
