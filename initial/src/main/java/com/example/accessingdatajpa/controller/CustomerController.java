package com.example.accessingdatajpa.controller;


import com.example.accessingdatajpa.domain.Customer;
import com.example.accessingdatajpa.dto.CustomerDTO;
import com.example.accessingdatajpa.mapper.CustomerMapper;
import com.example.accessingdatajpa.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers/")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List <Customer>> getAllCustomers(){
        List <Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional <Customer>> findById(@PathVariable("id") Long id){
        Optional <Customer> customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> save(@RequestBody CustomerDTO newCustomer) throws Exception {
        Customer customer = customerMapper.customerDTOToCustomer(newCustomer);
        customer = customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<Customer> deleteCustomer(@RequestBody CustomerDTO newCustomer) throws Exception {
        Customer customer = customerMapper.customerDTOToCustomer(newCustomer);
        customerService.delete(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Optional <Customer>> deleteCustomerById(@PathVariable("id") Long id) throws Exception{
        Optional <Customer> customer = customerService.findById(id);
        customerService.deleteById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
