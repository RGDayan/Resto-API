package com.dayan.restaurant.model;

import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.ProductView;
import com.dayan.restaurant.view.ServiceView;
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
    @JsonView({CardView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public Long id;

    @Column(nullable = false)
    @JsonView({CardView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public String title;

    @Column(nullable = false)
    @JsonView({CardView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public String type;

    @Column(name = "opening_time")
    @JsonView({CardView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public Time openingTime;

    @Column(name = "closing_time")
    @JsonView({CardView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public Time closingTime;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN")
    public Boolean isDeleted = false;

    @OneToMany(mappedBy = "card")
    @JsonIgnoreProperties("card")
    @JsonView({CardView.Index.class})
    public List<Service> services = new ArrayList<>();

    @ManyToMany(mappedBy = "cards", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("cards")
    @JsonView({CardView.Index.class, ServiceView.ShowCurrent.class})
    public List<Product> products = new ArrayList<>();

    public void addService(Service service){
        services.add(service);
        service.card = this;
    }

    public void removeService(Service service){
        services.remove(service);
        service.card = null;
    }

    public void addProduct(Product product){
        products.add(product);
        product.cards.add(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.cards.remove(this);
    }
}
