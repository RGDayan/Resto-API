package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.service.CardService;
import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/cards")
    @JsonView({CardView.Index.class})
    public Iterable<Card> getCards(){
        return cardService.getCards();
    }

    @GetMapping("/cards/types")
    public List<String> getCardsType(){
        return cardService.getCardTypes();
    }

    @GetMapping("/cards/{id}")
    @JsonView({CardView.Index.class})
    public Card getCard(@PathVariable("id") Long id){
        return cardService.getCard(id);
    }

    @GetMapping("/cards/{id}/products/{type}")
    @JsonView({ProductView.Index.class})
    public List<Product> getCardProductsByType(@PathVariable("id") Long id, @PathVariable("type") String type){
        return cardService.getCardProductsByType(id, type);
    }

    @PostMapping("/cards")
    @JsonView({CardView.Index.class})
    public Card postCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @PutMapping("/cards")
    @JsonView({CardView.Index.class})
    public Card putCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @PutMapping("/cards/{cardId}/product/{productId}")
    @JsonView({CardView.Index.class})
    public Card putCardProduct(@PathVariable("cardId") Long cardId, @PathVariable("productId") Long productId) {
        return cardService.addCardProduct(cardId, productId);
    }

    @DeleteMapping("/cards/{cardId}/product/{productId}")
    @JsonView({CardView.Index.class})
    public Card deleteCardProduct(@PathVariable("cardId") Long cardId, @PathVariable("productId") Long productId) {
        return cardService.removeCardProduct(cardId, productId);
    }

    @DeleteMapping("/cards/{id}")
    @JsonView({CardView.Index.class})
    public Card deleteCard(@PathVariable("id") Long id){
        return cardService.deleteCard(id);
    }
}
