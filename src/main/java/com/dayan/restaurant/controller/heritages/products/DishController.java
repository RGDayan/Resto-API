package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Dish;
import com.dayan.restaurant.service.heritages.product.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DishController {

    @Autowired
    public DishService dishService;

    @GetMapping("/products/dish")
    public Iterable<Dish> getDishes(){
        return dishService.getDishes();
    }
    @GetMapping("/products/dish/{id}")
    public Optional<Dish> getDish(@PathVariable("id") Long id){
        return dishService.getDish(id);
    }
    
    @PostMapping("/products/dish")
    public Dish postDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @PutMapping("/products/dish")
    public Dish putDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @DeleteMapping("/products/dish/{id}")
    public Dish deleteDish(@PathVariable("id") Long id){
        return dishService.deleteDish(id);
    }
}
