package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "beverage")
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double degree;

    @Column(nullable = false)
    private String type;

    @OneToOne
    private Product product;
}
