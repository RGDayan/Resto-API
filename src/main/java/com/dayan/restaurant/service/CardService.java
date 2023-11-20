package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Card;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.repository.CardRepository;
import com.dayan.restaurant.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ProductRepository productRepository;

    public Card getCard(final Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : enregistrement supprimé");

        card.products = card.products.stream().filter(x -> !x.isDeleted).toList();
        return card;
    }

    public List<Card> getCards(){
        List<Card> cards = (List<Card>)cardRepository.findAll();
        if (cards.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : aucune carte n'a été trouvée.");

        List<Card> existingCards = new ArrayList<>();
        for (Card card: cards) {
            if (!card.isDeleted)
                existingCards.add(card);
        }

        return existingCards;
    }

    public List<String> getCardTypes() {
        List<Card> cards = (List<Card>) cardRepository.findAll();
        List<String> types = new ArrayList<>();
        for (Card card: cards) {
            if (!types.contains(card.type))
                types.add(card.type);
        }

        return types;
    }

    public List<Product> getCardProductsByType(Long id, String type) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : aucune carte n'a été trouvée.");
        else if (card.isDeleted)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : carte supprimé");

        List<Product> productsByType = card.products.stream().filter((x) -> Objects.equals(x.productType, type) && !x.isDeleted).toList();
        if (productsByType.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : cette carte ne contient aucun produit de cette catégorie");

        return productsByType;
    }

    public Card deleteCard(final Long id){
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            Card existingCard = card.get();
            existingCard.isDeleted = true;
            cardRepository.save(existingCard);
            return existingCard;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : aucune carte n'a été trouvée.");
    }

    public Card saveCard(Card card){
        return saveCardThenReturnWithoutDeleted(card);
    }

    public Card addCardProduct(Long cardId, Long productId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null && !card.products.contains(product))
                card.addProduct(product);

            return saveCardThenReturnWithoutDeleted(card);
        }
        return null;
    }

    public Card removeCardProduct(Long cardId, Long productId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null && card.products.contains(product))
                card.removeProduct(product);

            return saveCardThenReturnWithoutDeleted(card);
        }
        return null;
    }

    private Card saveCardThenReturnWithoutDeleted(Card card) {
        Card savedCard = cardRepository.save(card);
        if (savedCard.products != null)
            savedCard.products = savedCard.products.stream().filter(x -> !x.isDeleted).toList();
        return savedCard;
    }
}