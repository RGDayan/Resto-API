package com.dayan.restaurant.repository;

import com.dayan.restaurant.model.Command;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends CrudRepository<Command, Long> {
}
