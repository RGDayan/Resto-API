package com.dayan.restaurant.repository;

import com.dayan.restaurant.model.RatingTVA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingTVARepository extends CrudRepository<RatingTVA, Long> {
}
