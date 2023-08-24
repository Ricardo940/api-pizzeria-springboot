package com.ramr.pizzeria.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ramr.pizzeria.persistence.entiity.Customer;
import com.ramr.pizzeria.service.CustomerService;
import com.ramr.pizzeria.web.controller.CustomerController;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControlerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerController customerController;
    @MockBean
    private CustomerService customerService;
   
    Customer CUSTOMER_01 = new Customer("1","Ricardo","calle 44b #20-16","ricardo@gmail.com","3148014430");
    Customer CUSTOMER_02 = new Customer("2","Maye","calle 44b #20-16","maye@gmail.com","321546854");
    Customer CUSTOMER_03 = new Customer("3","Marledys","calle 44b #20-16","marledys@gmail.com","215833255");

    @Test
    @DisplayName("List of customers")
    public void test() throws Exception{

        List<Customer> customers = new ArrayList<>(Arrays.asList(CUSTOMER_01,CUSTOMER_02,CUSTOMER_03));

        Mockito.when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders
              .get("/api/customers")
              .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[1].name", is("Maye")));
    }


    
}
