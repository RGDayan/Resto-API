package com.dayan.restaurant.repository.heritages.product;

import com.dayan.restaurant.model.heritages.products.Dessert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertRepository extends CrudRepository<Dessert, Long> {
}
