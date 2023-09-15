package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Starter;
import com.dayan.restaurant.service.heristages.product.StarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarterController {

    @Autowired
    public StarterService starterService;

    @GetMapping("/products/starters")
    public Iterable<Starter> getStarters(){
        return starterService.getStarters();
    }
}
