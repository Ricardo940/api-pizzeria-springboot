package com.ramr.pizzeria.persistence.repository;

import com.ramr.pizzeria.persistence.entiity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, String> {
    @Query(value = "SELECT c FROM Customer c WHERE c.phoneNumber = :phone")
    Customer findByPhone(@Param("phone") String phone);
}
