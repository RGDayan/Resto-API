package com.dayan.restaurant.service;

import com.dayan.restaurant.model.RatingTVA;
import com.dayan.restaurant.repository.RatingTVARepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RatingTVAService {
    @Autowired
    public RatingTVARepository ratingTVARepository;

    public List<RatingTVA> getRatings(){
        return (List<RatingTVA>) ratingTVARepository.findAll();
    }
}
