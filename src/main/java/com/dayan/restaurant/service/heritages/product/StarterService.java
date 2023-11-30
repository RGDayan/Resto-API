package com.dayan.restaurant.service.heritages.product;

import com.dayan.restaurant.model.heritages.products.Starter;
import com.dayan.restaurant.repository.RatingTVARepository;
import com.dayan.restaurant.repository.heritages.product.StarterRepository;
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
public class StarterService {

    @Autowired
    private StarterRepository starterRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private RatingTVARepository ratingTVARepository;
    @Autowired
    private ProductTools productTools;
    
    public Starter getStarter(final Long id) {
        Starter starter = starterRepository.findById(id).orElse(null);
        if (starter == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement non-trouvé");
        if (starter.isDeleted)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur : enregistrement supprimé");

        return starter;
    }

    public List<Starter> getStarters(){
        List<Starter> starters = (List<Starter>)starterRepository.findAll();
        if (starters.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune entrée n'a été trouvée.");

        List<Starter> existingStarters = new ArrayList<>();
        for (Starter starter :
                starters) {
            if (!starter.isDeleted)
                existingStarters.add(starter);
        }
        if (existingStarters.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune entrée n'a été trouvée.");

        return existingStarters;
    }

    public Starter deleteStarter(final Long id){
        Optional<Starter> starter = starterRepository.findById(id);
        if (starter.isPresent()) {
            Starter existingStarter = starter.get();
            existingStarter.isDeleted = true;
            starterRepository.save(existingStarter);
            return existingStarter;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune entrée n'a été trouvée.");
    }

    public Starter saveStarter(Starter starter){
        productTools.computeProductPrices(starter);
        return starterRepository.save(starter);
    }
}
