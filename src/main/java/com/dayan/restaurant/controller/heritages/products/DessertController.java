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

    @GetMapping("/products/desserts")
    public Iterable<Dessert> getDesserts(){
        return dessertService.getDesserts();
    }
    @GetMapping("/products/desserts/{id}")
    public Optional<Dessert> getDessert(@PathVariable("id") Long id){
        return dessertService.getDessert(id);
    }
    
    @PostMapping("/products/desserts")
    public Dessert postDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @PutMapping("/products/desserts")
    public Dessert putDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @DeleteMapping("/products/desserts/{id}")
    public Dessert deleteDessert(@PathVariable("id") Long id){
        return dessertService.deleteDessert(id);
    }
}
