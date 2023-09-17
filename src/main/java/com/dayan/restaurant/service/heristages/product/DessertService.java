package com.dayan.restaurant.service.heristages.product;

import com.dayan.restaurant.model.heritages.products.Dessert;
import com.dayan.restaurant.repository.heritages.product.DessertRepository;
import com.dayan.restaurant.service.ProductService;
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
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;
    @Autowired
    private ProductService productService;
    
    public Optional<Dessert> getDessert(final Long id) {
        Optional<Dessert> dessert = dessertRepository.findById(id);
        if (dessert.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement non-trouvé");
        if (dessert.get().getProduct().getIsDeleted())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur : enregistrement supprimé");

        return dessert;
    }

    public List<Dessert> getDesserts(){
        List<Dessert> desserts = (List<Dessert>)dessertRepository.findAll();
        if (desserts.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun dessert n'a été trouvé.");

        List<Dessert> existingDesserts = new ArrayList<>();
        for (Dessert dessert :
                desserts) {
            if (!dessert.getProduct().getIsDeleted())
                existingDesserts.add(dessert);
        }

        return existingDesserts;
    }

    public Dessert deleteDessert(final Long id){
        Optional<Dessert> dessert = dessertRepository.findById(id);
        if (dessert.isPresent()) {
            Dessert existingDessert = dessert.get();
            existingDessert.getProduct().setIsDeleted(true);
            dessertRepository.save(existingDessert);
            return existingDessert;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun dessert n'a été trouvé");
    }

    public Dessert saveDessert(Dessert dessert){
        productService.saveProduct(dessert.getProduct());
        return dessertRepository.save(dessert);
    }
}
