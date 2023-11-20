package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Starter;
import com.dayan.restaurant.service.heritages.product.StarterService;
import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StarterController {

    @Autowired
    public StarterService starterService;

    @GetMapping("/products/starter")
    @JsonView({ProductView.Index.class})
    public Iterable<Starter> getStarters(){
        return starterService.getStarters();
    }
    @GetMapping("/products/starter/{id}")
    @JsonView({ProductView.Index.class})
    public Optional<Starter> getStarter(@PathVariable("id") Long id){
        return starterService.getStarter(id);
    }
    
    @PostMapping("/products/starter")
    @JsonView({ProductView.Index.class})
    public Starter postStarter(@RequestBody Starter starter) {
        return starterService.saveStarter(starter);
    }

    @PutMapping("/products/starter")
    @JsonView({ProductView.Index.class})
    public Starter putStarter(@RequestBody Starter starter) {
        return starterService.saveStarter(starter);
    }

    @DeleteMapping("/products/starter/{id}")
    @JsonView({ProductView.Index.class})
    public Starter deleteStarter(@PathVariable("id") Long id){
        return starterService.deleteStarter(id);
    }
}
