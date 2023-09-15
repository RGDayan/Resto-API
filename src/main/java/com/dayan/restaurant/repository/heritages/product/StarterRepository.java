package com.dayan.restaurant.repository.heritages.product;

import com.dayan.restaurant.model.heritages.products.Starter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarterRepository extends CrudRepository<Starter, Long> {
}
