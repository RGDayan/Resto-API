package com.dayan.restaurant.tools;

import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.model.RatingTVA;
import com.dayan.restaurant.repository.RatingTVARepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Data
@Service
public class ProductTools {
    @Autowired
    private RatingTVARepository ratingTVARepository;

    public void computeProductPrices(Product product) {
        List<RatingTVA> ratings = (List<RatingTVA>) ratingTVARepository.findAll();
        if (ratings.stream().noneMatch(x -> x.id.equals(product.ratingTVA.id) && x.rating.equals(product.ratingTVA.rating)))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : le taux de TVA ne correspond à aucun taux existant");

        product.partTVA = product.priceHT * (product.ratingTVA.rating / 100);
        product.partTVA = Math.floor(product.partTVA * 100) / 100;
        product.priceTTC = Math.floor((product.priceHT + product.partTVA) * 100 ) / 100;
    }
}
