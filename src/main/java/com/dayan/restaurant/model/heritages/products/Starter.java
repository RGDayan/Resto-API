package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "starter")
public class Starter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_hot", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isHot;

    @OneToOne
    private Product product;
}
