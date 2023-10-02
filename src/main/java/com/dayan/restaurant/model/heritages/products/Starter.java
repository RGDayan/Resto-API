package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("starter")
@Table(name = "starter")
public class Starter extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_hot", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isHot;
}
