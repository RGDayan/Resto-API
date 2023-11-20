package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.repository.CommandRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    public Command getCommand(final Long id){
        Command command = commandRepository.findById(id).orElse(null);
        if (command == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur : Commande non existante");

        return command;
    }

    public Iterable<Command> getCommands(){
        return commandRepository.findAll();
    }

    public Command saveCommand(Command command){
        return commandRepository.save(command);
    }

    public Command addCommandProduct(Long id, Product product) {
        Command command = commandRepository.findById(id).orElse(null);
        if (command == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : commande non trouvée");
        if (command.status)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ATTENTION : commande déjà cloturée");

        CommandProduct commandProduct = new CommandProduct();
        commandProduct.command = command;
        commandProduct.product = product;
        commandProduct.status = "ordered";
        commandProduct.orderedHour = LocalDateTime.now();
        commandProduct.quantity = 1;
        command.products.add(commandProduct);

        return commandRepository.save(command);
    }

    public void deleteCommand(final Long id) {
        commandRepository.deleteById(id);
    }
}
