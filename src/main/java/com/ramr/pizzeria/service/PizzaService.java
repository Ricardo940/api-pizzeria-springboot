package com.ramr.pizzeria.service;

import com.ramr.pizzeria.persistence.entiity.Pizza;
import com.ramr.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.ramr.pizzeria.persistence.repository.PizzaRepository;
import com.ramr.pizzeria.service.dto.UpdatePizzaPriceDto;
import com.ramr.pizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<Pizza> getAvailable(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public Pizza getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()-> new RuntimeException("La pizza no existe"));
    }

    public List<Pizza> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public Pizza get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public Pizza save(Pizza pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional(noRollbackFor = EmailApiException.class,
    propagation = Propagation.REQUIRED)
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
        this.sendEmail();
    }

    public void sendEmail(){
        throw new EmailApiException();
    }

    public boolean exists(int idPizza){
        return pizzaRepository.existsById(idPizza);
    }

    /* private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Pizza> getAll(){
        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(Pizza.class));
    }
 */

}
