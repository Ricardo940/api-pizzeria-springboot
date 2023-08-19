package com.ramr.pizzeria.persistence.repository;

import com.ramr.pizzeria.persistence.entiity.Pizza;
import com.ramr.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {

    List<Pizza> findAllByAvailableTrueOrderByPrice();
    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String descripcion);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String descripcion);
    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();

    @Query(value =
            "UPDATE pizza " +
                    "SET price=:#{#newPizzaPrice.newPrice} " +
                    "WHERE id_pizza=:#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
