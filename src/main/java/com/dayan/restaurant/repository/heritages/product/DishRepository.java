package com.dayan.restaurant.repository.heritages.product;

import com.dayan.restaurant.model.heritages.products.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
}
