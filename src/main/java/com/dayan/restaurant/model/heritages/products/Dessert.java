package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("dessert")
@Table(name = "dessert")
public class Dessert extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_hot", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isHot;

    @Column(name = "is_flambe", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isFlambe;
}
