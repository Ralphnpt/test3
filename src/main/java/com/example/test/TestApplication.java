package com.example.test;

import com.example.test.entities.Customer;
import com.example.test.respository.CustomerRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRespository customerRespository){
		return args -> {
			customerRespository.save(new Customer(null, "Jam", new Date(), 100.0));
			customerRespository.save(new Customer(null, "Jen", new Date(), 200.0));
			customerRespository.save(new Customer(null, "Jok", new Date(), 300.0));
			customerRespository.save(new Customer(null, "Jos", new Date(), 400.0));
			customerRespository.findAll().forEach(p->{ System.out.println(p.getName());
			});
		};
	}
}
