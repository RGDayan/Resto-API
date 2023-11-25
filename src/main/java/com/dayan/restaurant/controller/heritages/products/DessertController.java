package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Dessert;
import com.dayan.restaurant.service.heritages.product.DessertService;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DessertController {

    @Autowired
    public DessertService dessertService;

    @GetMapping("/products/dessert")
    @JsonView({ProductView.Index.class})
    public Iterable<Dessert> getDesserts(){
        return dessertService.getDesserts();
    }
    @GetMapping("/products/dessert/{id}")
    @JsonView({ProductView.Index.class})
    public Optional<Dessert> getDessert(@PathVariable("id") Long id){
        return dessertService.getDessert(id);
    }
    
    @PostMapping("/products/dessert")
    @JsonView({ProductView.Index.class})
    public Dessert postDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @PutMapping("/products/dessert")
    @JsonView({ProductView.Index.class})
    public Dessert putDessert(@RequestBody Dessert dessert) {
        return dessertService.saveDessert(dessert);
    }

    @DeleteMapping("/products/dessert/{id}")
    @JsonView({ProductView.Index.class})
    public Dessert deleteDessert(@PathVariable("id") Long id){
        return dessertService.deleteDessert(id);
    }
}
