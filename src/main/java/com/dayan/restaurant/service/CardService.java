package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.repository.CardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Optional<Card> getCard(final Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent() && !card.get().getIsDeleted())
            return card;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement supprimé");
    }

    public List<Card> getCards(){
        List<Card> cards = (List<Card>)cardRepository.findAll();
        if (cards.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune carte n'a été trouvée.");
        return cards;
    }

    public Card deleteCard(final Long id){
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            Card existingCard = card.get();
            existingCard.setIsDeleted(true);
            cardRepository.save(existingCard);
            return existingCard;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune carte n'a été trouvée.");
    }

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    public List<String> getCardTypes() {
        List<Card> cards = (List<Card>) cardRepository.findAll();
        List<String> types = new ArrayList<>();
        for (Card card: cards) {
            if (!types.contains(card.getType()))
                types.add(card.getType());
        }

        return types;
    }
}