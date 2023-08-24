package com.ramr.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@EnableJpaAuditing
public class RamrPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamrPizzeriaApplication.class, args);
	}

}
