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

    @GetMapping("/products/dishes")
    public Iterable<Dish> getDishes(){
        return dishService.getDishes();
    }
    @GetMapping("/products/dishes/{id}")
    public Optional<Dish> getDish(@PathVariable("id") Long id){
        return dishService.getDish(id);
    }
    
    @PostMapping("/products/dishes")
    public Dish postDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @PutMapping("/products/dishes")
    public Dish putDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @DeleteMapping("/products/dishes/{id}")
    public Dish deleteDish(@PathVariable("id") Long id){
        return dishService.deleteDish(id);
    }
}
