package com.jonathan.api_rest_coffee_shop.controllers;

import com.jonathan.api_rest_coffee_shop.models.Coffee;
import com.jonathan.api_rest_coffee_shop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")

public class CoffeeController {
    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }
    @PostMapping(value = "/createCoffee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coffee createNewCoffee(@RequestBody Coffee coffee){
        Coffee createCoffee = new Coffee();
        createCoffee.setName(coffee.getName());
        createCoffee.setPrice(coffee.getPrice());
        return coffeeRepository.save(createCoffee);
    }
    @PutMapping(value = "updatedCoffee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coffee updatedCoffee(@RequestBody Coffee coffee){
        Coffee getCoffee = coffeeRepository.findById(coffee.getId()).orElseThrow();

        Coffee updatedCoffee = new Coffee();
        updatedCoffee.setId(coffee.getId());
        updatedCoffee.setName(coffee.getName());
        updatedCoffee.setPrice(coffee.getPrice());
        return coffeeRepository.save(updatedCoffee);
    }
    @DeleteMapping(value = "deletedCoffee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coffee deletedCoffee(@PathVariable Long id){
        Coffee getCoffee = coffeeRepository.findById(id).orElseThrow();
        coffeeRepository.delete(getCoffee);
        return getCoffee;
    }
}
