package com.ramr.pizzeria.web.controller;

import com.ramr.pizzeria.persistence.entiity.Pizza;
import com.ramr.pizzeria.service.PizzaService;
import com.ramr.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<Pizza>> getPizzas(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "8") int elements){
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<Pizza>> getPizzasAvailable(@RequestParam (defaultValue = "0") int page,
                                                          @RequestParam (defaultValue = "8") int elements,
                                                          @RequestParam (defaultValue = "price") String sortBy,
                                                          @RequestParam (defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy,sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pizza> getByName(@PathVariable String name){
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(pizzaService.getWithout(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<Pizza>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<Pizza> getPizza(@PathVariable int idPizza){
        return ResponseEntity.ok(pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<Pizza> savePizza(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if(this.pizzaService.exists(dto.getPizzaId())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizza(@PathVariable int idPizza){
        if(pizzaService.exists(idPizza)){
            pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
