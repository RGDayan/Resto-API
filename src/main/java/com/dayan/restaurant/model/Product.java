package com.dayan.restaurant.model;

import com.dayan.restaurant.model.relations.OrderProduct;
import com.dayan.restaurant.view.OrderView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    public Long id;

    @Column(nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    public String label;

    @JsonView({OrderView.Index.class, ProductView.Index.class})
    public String description;

    @Column(nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    public Double price;

    @Column(name = "is_active", columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    public Boolean isActive = true;

    @ManyToMany
    @JoinTable(name = "card_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "card_id"))
    @JsonIgnoreProperties("products")
    @JsonView(ProductView.Index.class)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonView(ProductView.Index.class)
    private List<OrderProduct> orders = new ArrayList<>();
}
