package com.dayan.restaurant.model;

import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.dayan.restaurant.view.RatingTVAView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rating_tva")
public class RatingTVA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ProductView.Index.class, RatingTVAView.Index.class, CardView.Index.class, CommandView.Index.class})
    public Long id;

    @Column(nullable = false)
    @JsonView({ProductView.Index.class, RatingTVAView.Index.class, CardView.Index.class, CommandView.Index.class})
    public Double rating;

    @OneToMany(mappedBy = "ratingTVA")
    @JsonIgnoreProperties("ratingTVA")
    @JsonView({ProductView.Index.class})
    public List<Product> products = new ArrayList<>();
}
