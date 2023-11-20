package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("beverage")
@Table(name = "beverage")
public class Beverage extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    @JsonView({ProductView.Index.class})
    public Double degree;

    @Column(nullable = false)
    @JsonView({ProductView.Index.class})
    public String type;
}
