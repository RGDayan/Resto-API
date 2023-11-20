package com.dayan.restaurant.model;

import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Long id;

    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Double amount;

    @Column(name = "num_table", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Integer numTable;

    @Column(name = "customer_count", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Integer customerCount;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Boolean status = false;

    @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("commands")
    @JsonView(CommandView.Index.class)
    public Service service;

    @OneToMany(mappedBy = "command", fetch = FetchType.EAGER)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class})
    public List<CommandProduct> products = new ArrayList<>();
}
