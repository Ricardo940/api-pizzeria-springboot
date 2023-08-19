package com.ramr.pizzeria.persistence.repository;

import com.ramr.pizzeria.persistence.entiity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, Integer> {
    Page<Pizza> findByAvailableTrue(Pageable pageable);
}
