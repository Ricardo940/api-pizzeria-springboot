package com.ramr.pizzeria.persistence.repository;

import com.ramr.pizzeria.persistence.entiity.UserRole;
import com.ramr.pizzeria.persistence.entiity.UserRoleId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
}
