package com.dayan.restaurant.service.heristages.product;

import com.dayan.restaurant.model.heritages.products.Beverage;
import com.dayan.restaurant.repository.heritages.product.BeverageRepository;
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
public class BeverageService {

    @Autowired
    private BeverageRepository beverageRepository;
    @Autowired
    private ProductService productService;
    
    public Optional<Beverage> getBeverage(final Long id) {
        Optional<Beverage> beverage = beverageRepository.findById(id);
        if (beverage.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement non-trouvé");
        if (beverage.get().getProduct().getIsDeleted())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur : enregistrement supprimé");

        return beverage;
    }

    public List<Beverage> getBeverages(){
        List<Beverage> beverages = (List<Beverage>)beverageRepository.findAll();
        if (beverages.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune boisson n'a été trouvée.");

        List<Beverage> existingBeverages = new ArrayList<>();
        for (Beverage beverage :
                beverages) {
            if (!beverage.getProduct().getIsDeleted())
                existingBeverages.add(beverage);
        }

        return existingBeverages;
    }

    public Beverage deleteBeverage(final Long id){
        Optional<Beverage> beverage = beverageRepository.findById(id);
        if (beverage.isPresent()) {
            Beverage existingBeverage = beverage.get();
            existingBeverage.getProduct().setIsDeleted(true);
            beverageRepository.save(existingBeverage);
            return existingBeverage;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune boisson n'a été trouvée.");
    }

    public Beverage saveBeverage(Beverage beverage){
        productService.saveProduct(beverage.getProduct());
        return beverageRepository.save(beverage);
    }

    public List<String> getBeveragesTypes() {
        List<Beverage> beverages = (List<Beverage>) beverageRepository.findAll();
        List<String> types = new ArrayList<>();
        for (Beverage beverage: beverages) {
            if (!types.contains(beverage.getType()))
                types.add(beverage.getType());
        }

        return types;
    }
}
