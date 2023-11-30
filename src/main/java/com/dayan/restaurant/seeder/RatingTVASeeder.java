package com.dayan.restaurant.seeder;

import com.dayan.restaurant.model.RatingTVA;
import com.dayan.restaurant.repository.RatingTVARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingTVASeeder {
    @Autowired
    public RatingTVARepository ratingTVARepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (!((List<RatingTVA>)ratingTVARepository.findAll()).isEmpty())
            return;

        List<RatingTVA> ratings = new ArrayList<>();
        RatingTVA tva5 = new RatingTVA();
        tva5.rating = 5.5;
        RatingTVA tva10 = new RatingTVA();
        tva10.rating = 10d;
        RatingTVA tva20 = new RatingTVA();
        tva20.rating = 20d;
        ratings.add(tva5);
        ratings.add(tva10);
        ratings.add(tva20);

        for (RatingTVA rating : ratings) {
            ratingTVARepository.save(rating);
        }
    }
}
