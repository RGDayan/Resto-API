package com.dayan.restaurant.model;

import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CommandView.Index.class, ProductView.Index.class})
    private Long id;

    @JsonView({CommandView.Index.class, ProductView.Index.class})
    private Double amount;

    @Column(name = "num_table", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class})
    private Integer numTable;

    @Column(name = "customer_count", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class})
    private Integer customerCount;

    @Column(nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class})
    private String status;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("commands")
    @JsonView(CommandView.Index.class)
    private Service service;

    @OneToMany(mappedBy = "command", fetch = FetchType.EAGER)
    @JsonView(CommandView.Index.class)
    private List<CommandProduct> products = new ArrayList<>();
}
