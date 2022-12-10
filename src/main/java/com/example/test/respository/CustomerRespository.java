package com.example.test.respository;

import com.example.test.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRespository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerById(long kw);
}
