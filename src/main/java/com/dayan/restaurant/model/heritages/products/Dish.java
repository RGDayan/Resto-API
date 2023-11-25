package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("dish")
@Table(name = "dish")
public class Dish extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
