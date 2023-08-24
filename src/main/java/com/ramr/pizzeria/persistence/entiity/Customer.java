package com.ramr.pizzeria.persistence.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(length = 100)
    private String address;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
