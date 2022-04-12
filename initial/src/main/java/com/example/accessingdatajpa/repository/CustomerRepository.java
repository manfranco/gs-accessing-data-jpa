package com.example.accessingdatajpa.repository;

import java.util.List;

import com.example.accessingdatajpa.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    List<Customer> findAll();
    Customer findById(long id);
}