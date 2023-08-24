package com.ramr.pizzeria.service;

import com.ramr.pizzeria.persistence.entiity.Customer;
import com.ramr.pizzeria.persistence.repository.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }

    public Customer save(Customer customer){
        return this.customerRepository.save(customer);
    }

}
