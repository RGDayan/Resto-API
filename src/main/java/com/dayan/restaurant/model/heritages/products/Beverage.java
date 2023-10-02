package com.dayan.restaurant.model.heritages.products;

import com.dayan.restaurant.model.Product;
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
    public Double degree;

    @Column(nullable = false)
    public String type;
}
