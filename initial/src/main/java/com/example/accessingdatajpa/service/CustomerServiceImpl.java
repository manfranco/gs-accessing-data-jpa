package com.example.accessingdatajpa.service;

import com.example.accessingdatajpa.domain.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.example.accessingdatajpa.util.exception.EntityNotFoundException;
import com.example.accessingdatajpa.util.exception.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Scope("singleton")
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Validator validator;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void validate(Customer customer) throws Exception {
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    public Customer save(Customer customer) throws Exception {

        if(customer == null){
            throw new EntityNotFoundException("No Customer was provided");
        }

        validate(customer);

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) throws Exception {
        if(customer == null){
            throw new EntityNotFoundException("No Customer was provided");
        }

        if(customerRepository.existsById(customer.getId())){
            throw new EntityNotFoundException("Customer not found");
        }

        validate(customer);

        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if(id == null){
            throw new ParameterNotFoundException("No Id provided");
        }

        Optional <Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.delete(customer.get());
        } else{
            throw new ParameterNotFoundException("Customer not found");
        }
    }
}
