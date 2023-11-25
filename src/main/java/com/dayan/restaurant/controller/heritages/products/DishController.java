package com.dayan.restaurant.controller.heritages.products;

import com.dayan.restaurant.model.heritages.products.Dish;
import com.dayan.restaurant.service.heritages.product.DishService;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DishController {

    @Autowired
    public DishService dishService;

    @GetMapping("/products/dish")
    @JsonView({ProductView.Index.class})
    public Iterable<Dish> getDishes(){
        return dishService.getDishes();
    }
    @GetMapping("/products/dish/{id}")
    @JsonView({ProductView.Index.class})
    public Optional<Dish> getDish(@PathVariable("id") Long id){
        return dishService.getDish(id);
    }
    
    @PostMapping("/products/dish")
    @JsonView({ProductView.Index.class})
    public Dish postDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @PutMapping("/products/dish")
    @JsonView({ProductView.Index.class})
    public Dish putDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @DeleteMapping("/products/dish/{id}")
    @JsonView({ProductView.Index.class})
    public Dish deleteDish(@PathVariable("id") Long id){
        return dishService.deleteDish(id);
    }
}
