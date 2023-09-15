package com.dayan.restaurant.service.heristages.product;

import com.dayan.restaurant.model.heritages.products.Starter;
import com.dayan.restaurant.repository.heritages.product.StarterRepository;
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
    
    public Optional<Starter> getStarter(final Long id) {
        Optional<Starter> starter = starterRepository.findById(id);
        if (starter.isPresent() && !starter.get().getProduct().getIsDeleted())
            return starter;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : enregistrement supprimé");
    }

    public List<Starter> getStarters(){
        List<Starter> starters = (List<Starter>)starterRepository.findAll();
        if (starters.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune entrée n'a été trouvée.");

        List<Starter> existingStarters = new ArrayList<>();
        for (Starter starter :
                starters) {
            if (!starter.getProduct().getIsDeleted())
                existingStarters.add(starter);
        }

        return existingStarters;
    }

    public Starter deleteStarter(final Long id){
        Optional<Starter> starter = starterRepository.findById(id);
        if (starter.isPresent()) {
            Starter existingStarter = starter.get();
            existingStarter.getProduct().setIsDeleted(true);
            starterRepository.save(existingStarter);
            return existingStarter;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attention : aucune entrée n'a été trouvée.");
    }

    public Starter saveStarter(Starter starter){
        return starterRepository.save(starter);
    }
}