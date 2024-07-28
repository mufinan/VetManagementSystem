package dev.patika.service;

import dev.patika.dto.CustomerDTO;
import dev.patika.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    List<Customer> findCustomersByName(String name);
}

