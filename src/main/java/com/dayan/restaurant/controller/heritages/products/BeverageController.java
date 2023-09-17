package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Beverage;
import com.dayan.restaurant.service.heristages.product.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BeverageController {

    @Autowired
    public BeverageService beverageService;

    @GetMapping("/products/beverages")
    public Iterable<Beverage> getBeverages(){
        return beverageService.getBeverages();
    }
    @GetMapping("/products/beverages/{id}")
    public Optional<Beverage> getBeverage(@PathVariable("id") Long id){
        return beverageService.getBeverage(id);
    }
    @GetMapping("/products/beverages/types")
    public List<String> getCardsType(){
        return beverageService.getBeveragesTypes();
    }

    @PostMapping("/products/beverages")
    public Beverage postBeverage(@RequestBody Beverage beverage) {
        return beverageService.saveBeverage(beverage);
    }

    @PutMapping("/products/beverages")
    public Beverage putBeverage(@RequestBody Beverage beverage) {
        return beverageService.saveBeverage(beverage);
    }

    @DeleteMapping("/products/beverages/{id}")
    public Beverage deleteBeverage(@PathVariable("id") Long id){
        return beverageService.deleteBeverage(id);
    }
}
