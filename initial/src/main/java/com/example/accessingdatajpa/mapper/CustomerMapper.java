package com.example.accessingdatajpa.mapper;

import com.example.accessingdatajpa.domain.Customer;
import com.example.accessingdatajpa.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    public CustomerDTO customerToCustomerDTO(Customer customer);

    public Customer customerDTOToCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customers);

    public List<Customer> customerDTOListToCustomerDTOList(List<CustomerDTO> customerDTOS);
}
