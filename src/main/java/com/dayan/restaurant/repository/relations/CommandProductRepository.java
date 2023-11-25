package com.dayan.restaurant.repository.relations;

import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.model.relations.CommandProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, CommandProductId> {
}
