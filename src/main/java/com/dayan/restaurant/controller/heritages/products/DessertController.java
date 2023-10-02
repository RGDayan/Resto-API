package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Dessert;
import com.dayan.restaurant.service.heritages.product.DessertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DessertController {

    @Autowired
    public DessertService dessertService;

    @GetMapping("/products/dessert")
    public Iterable<Dessert> getDesserts(){
        return dessertService.getDesserts();
    }
    @GetMapping("/products/dessert/{id}")
    public Optional<Dessert> getDessert(@PathVariable("id") Long id){
        return dessertService.getDessert(id);
    }
    
    @PostMapping("/products/dessert")
    public Dessert postDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @PutMapping("/products/dessert")
    public Dessert putDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @DeleteMapping("/products/dessert/{id}")
    public Dessert deleteDessert(@PathVariable("id") Long id){
        return dessertService.deleteDessert(id);
    }
}
