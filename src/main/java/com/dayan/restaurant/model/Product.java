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

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Long id;

    @Column(nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public String label;

    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public String description;

    @Column(nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Double price;

    @Column(name = "is_active", columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Boolean isActive = true;

    @Column(name = "product_type", insertable = false, updatable = false, nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public String productType;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN")
    public Boolean isDeleted = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "card_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "card_id"))
    @JsonIgnoreProperties("products")
    @JsonView(ProductView.Index.class)
    public List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonView(ProductView.Index.class)
    public List<CommandProduct> commandProducts = new ArrayList<>();

}
