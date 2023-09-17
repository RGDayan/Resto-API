package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Starter;
import com.dayan.restaurant.service.heristages.product.StarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StarterController {

    @Autowired
    public StarterService starterService;

    @GetMapping("/products/starters")
    public Iterable<Starter> getStarters(){
        return starterService.getStarters();
    }
    @GetMapping("/products/starters/{id}")
    public Optional<Starter> getStarter(@PathVariable("id") Long id){
        return starterService.getStarter(id);
    }
    
    @PostMapping("/products/starters")
    public Starter postStarter(@RequestBody Starter starter) {
        return starterService.saveStarter(starter);
    }

    @PutMapping("/products/starters")
    public Starter putStarter(@RequestBody Starter starter) {
        return starterService.saveStarter(starter);
    }

    @DeleteMapping("/products/starters/{id}")
    public Starter deleteStarter(@PathVariable("id") Long id){
        return starterService.deleteStarter(id);
    }
}
