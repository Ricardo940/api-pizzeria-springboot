package com.ramr.pizzeria.persistence.repository;

import com.ramr.pizzeria.persistence.entiity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
