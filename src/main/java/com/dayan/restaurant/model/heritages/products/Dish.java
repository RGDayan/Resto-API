package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cooking;

    @OneToOne
    private Product product;
}
