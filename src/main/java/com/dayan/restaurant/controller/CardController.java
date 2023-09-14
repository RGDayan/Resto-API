package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    public Iterable<Card> getCards(){
        Iterable<Card> cards = cardService.getCards();
        List<Card> existingCards = new ArrayList<>();
        for (Card card :
                cards) {
            if (!card.getIsDeleted())
                existingCards.add(card);
        }
        return existingCards;
    }

    @GetMapping("/cards/types")
    public List<String> getCardsType(){
        return cardService.getCardTypes();
    }

    @GetMapping(path = "/cards/{id}")
    public Optional<Card> getCards(@PathVariable("id") Long id){
        return cardService.getCard(id);
    }

    @PostMapping("/cards")
    public Card postCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @DeleteMapping("/cards/{id}")
    public Card deleteCard(@PathVariable("id") Long id){
        return cardService.deleteCard(id);
    }
}
