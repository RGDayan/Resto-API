package com.dayan.restaurant.repository.heritages.product;

import com.dayan.restaurant.model.heritages.products.Beverage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Long> {
}
