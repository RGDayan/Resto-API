package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.repository.CommandRepository;
import com.dayan.restaurant.repository.ProductRepository;
import com.dayan.restaurant.repository.relations.CommandProductRepository;
import lombok.Data;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Data
@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommandProductRepository commandProductRepository;

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

    public void addCommandProduct(Long commandId, Long productId) {
        Command command = commandRepository.findById(commandId).orElse(null);
        if (command == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : commande non trouvée");
        if (command.status)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ATTENTION : commande déjà cloturée");

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : produit non trouvé");
        if (product.isDeleted)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ATTENTION : produit supprimé");

        CommandProduct commandProduct = command.addProduct(product);

        commandProductRepository.save(commandProduct);

        Command c = commandRepository.findById(commandId).orElse(null);
        assert c != null;
        c.computeAmounts();
        commandRepository.save(c);
    }

    public void reduceCommandProduct(Long commandId, Long productId) {
        Command command = commandRepository.findById(commandId).orElse(null);
        if (command == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : commande non trouvée");
        if (command.status)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ATTENTION : commande déjà cloturée");

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : produit non trouvé");
        if (product.isDeleted)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ATTENTION : produit supprimé");

        Pair<CommandProduct, String> commandProduct = command.reduceProduct(product);

        if (commandProduct.b.equals("remove")) {
            commandProductRepository.delete(commandProduct.a);
            command.commandProducts.removeIf(x -> commandProduct.a.product.equals(x.product));
        }
        else
            commandProductRepository.save(commandProduct.a);

        command.computeAmounts();
        commandRepository.save(command);
    }

    public void askProductCategory(Long id, String productType) {
        Command command = commandRepository.findById(id).orElse(null);
        assert command != null;

        for (CommandProduct commandProduct: command.commandProducts) {
            if (commandProduct.product.productType.equals(productType)){
                commandProduct.status = "asked";
                commandProductRepository.save(commandProduct);
            }
        }
    }

    public void deleteCommand(final Long id) {
        commandRepository.deleteById(id);
    }

    public void closeCommand(Long id, String reason) {
        Command command = commandRepository.findById(id).orElse(null);
        assert command != null;
        command.status = true;
        command.closingReason = reason;
        commandRepository.save(command);
    }
}
