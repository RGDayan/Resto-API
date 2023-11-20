package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("starter")
@Table(name = "starter")
public class Starter extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "is_hot", columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({ProductView.Index.class})
    public Boolean isHot;
}
