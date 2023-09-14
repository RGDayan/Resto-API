package com.dayan.restaurant.model;

import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ProductView.Index.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(ProductView.Index.class)
    private String title;

    @Column(nullable = false)
    @JsonView(ProductView.Index.class)
    private String type;

    @Column(name = "opening_time")
    @JsonView(ProductView.Index.class)
    private Time openingTime;

    @Column(name = "closing_time")
    @JsonView(ProductView.Index.class)
    private Time closingTime;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "card")
    @JsonIgnoreProperties("card")
    private List<Service> services = new ArrayList<>();

    @ManyToMany(mappedBy = "cards")
    @JsonIgnoreProperties("cards")
    private List<Product> products = new ArrayList<>();

    public void addService(Service service){
        services.add(service);
        service.setCard(this);
    }

    public void removeService(Service service){
        services.remove(service);
        service.setCard(null);
    }

    public void addProduct(Product product){
        products.add(product);
        product.getCards().add(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.getCards().remove(this);
    }
}
