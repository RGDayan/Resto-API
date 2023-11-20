package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("dessert")
@Table(name = "dessert")
public class Dessert extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "is_hot", columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({ProductView.Index.class})
    public Boolean isHot;

    @Column(name = "is_flambe", columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({ProductView.Index.class})
    public Boolean isFlambe;
}
