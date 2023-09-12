package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public Iterable<Card> getCards(){
        return cardService.getCards();
    }
}
