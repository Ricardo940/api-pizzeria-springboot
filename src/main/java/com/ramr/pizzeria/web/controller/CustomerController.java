package com.ramr.pizzeria.web.controller;

import com.ramr.pizzeria.persistence.entiity.Customer;
import com.ramr.pizzeria.persistence.entiity.Order;
import com.ramr.pizzeria.service.CustomerService;
import com.ramr.pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
}
