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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    private Long id;

    @JsonView({OrderView.Index.class, ProductView.Index.class})
    private Double amount;

    @Column(name = "num_table", nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    private Integer numTable;

    @Column(name = "customer_count", nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    private Integer customerCount;

    @Column(nullable = false)
    @JsonView({OrderView.Index.class, ProductView.Index.class})
    private String status;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("orders")
    @JsonView(OrderView.Index.class)
    private Service service;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @JsonView(OrderView.Index.class)
    private List<OrderProduct> products = new ArrayList<>();
}
