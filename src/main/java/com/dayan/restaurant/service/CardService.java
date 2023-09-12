package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.repository.CardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Optional<Card> getCard(final Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> getCards(){
        return (List<Card>)cardRepository.findAll();
    }

    public void deleteCard(final Long id){
        cardRepository.deleteById(id);
    }

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }
}