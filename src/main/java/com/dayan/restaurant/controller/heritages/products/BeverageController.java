package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Beverage;
import com.dayan.restaurant.service.heritages.product.BeverageService;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BeverageController {

    @Autowired
    public BeverageService beverageService;

    @GetMapping("/products/beverage")
    @JsonView({ProductView.Index.class})
    public Iterable<Beverage> getBeverages(){
        return beverageService.getBeverages();
    }
    @GetMapping("/products/beverage/{id}")
    @JsonView({ProductView.Index.class})
    public Optional<Beverage> getBeverage(@PathVariable("id") Long id){
        return beverageService.getBeverage(id);
    }
    @GetMapping("/products/beverage/types")
    public List<String> getCardsType(){
        return beverageService.getBeveragesTypes();
    }

    @PostMapping("/products/beverage")
    @JsonView({ProductView.Index.class})
    public Beverage postBeverage(@RequestBody Beverage beverage) {
        return beverageService.saveBeverage(beverage);
    }

    @PutMapping("/products/beverage")
    @JsonView({ProductView.Index.class})
    public Beverage putBeverage(@RequestBody Beverage beverage) {
        return beverageService.saveBeverage(beverage);
    }

    @DeleteMapping("/products/beverage/{id}")
    @JsonView({ProductView.Index.class})
    public Beverage deleteBeverage(@PathVariable("id") Long id){
        return beverageService.deleteBeverage(id);
    }
}
