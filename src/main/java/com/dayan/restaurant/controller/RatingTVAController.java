package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.RatingTVA;
import com.dayan.restaurant.service.RatingTVAService;
import com.dayan.restaurant.view.RatingTVAView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingTVAController {
    @Autowired
    public RatingTVAService ratingTVAService;

    @GetMapping("/ratingsTVA")
    @JsonView({RatingTVAView.Index.class})
    public List<RatingTVA> getRatings(){
        return ratingTVAService.getRatings();
    }
}
