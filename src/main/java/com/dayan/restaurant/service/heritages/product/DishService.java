package com.dayan.restaurant.service.heritages.product;

import com.dayan.restaurant.model.heritages.products.Dish;
import com.dayan.restaurant.repository.heritages.product.DishRepository;
import com.dayan.restaurant.service.ProductService;
import com.dayan.restaurant.tools.ProductTools;
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
public class DishService {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTools productTools;
    
    public Optional<Dish> getDish(final Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement non-trouvé");
        if (dish.get().isDeleted)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur : enregistrement supprimé");

        return dish;
    }

    public List<Dish> getDishes(){
        List<Dish> dishes = (List<Dish>)dishRepository.findAll();
        if (dishes.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun plat n'a été trouvé.");

        List<Dish> existingDishes = new ArrayList<>();
        for (Dish dish :
                dishes) {
            if (!dish.isDeleted)
                existingDishes.add(dish);
        }
        if (existingDishes.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun plat n'a été trouvé.");

        return existingDishes;
    }

    public Dish deleteDish(final Long id){
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            Dish existingDish = dish.get();
            existingDish.isDeleted = true;
            dishRepository.save(existingDish);
            return existingDish;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucun plat n'a été trouvé.");
    }

    public Dish saveDish(Dish dish){
        productTools.computeProductPrices(dish);
        return dishRepository.save(dish);
    }
}
