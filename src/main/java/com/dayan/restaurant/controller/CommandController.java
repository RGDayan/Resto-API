package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Service;
import com.dayan.restaurant.service.CommandService;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class CommandController {
    @Autowired
    private CommandService commandService;
    @Autowired
    private ServiceController serviceController;

    @GetMapping("/commands")
    @JsonView(CommandView.Index.class)
    public Iterable<Command> getCommands(){
        return commandService.getCommands();
    }

    @GetMapping("/commands/{id}")
    @JsonView(CommandView.Index.class)
    public Command getCommand(@PathVariable("id") Long id) {
        Command command = commandService.getCommand(id);
        command.commandProducts.sort((o1, o2) -> {
            switch (o1.product.productType) {
                case "starter":
                    return o2.product.productType.equals("starter") ? 0: -1;
                case "dish":
                    return o2.product.productType.equals("starter") ?
                            1:
                            o2.product.productType.equals("dish") ? 0: -1;
                case "dessert":
                    return o2.product.productType.equals("beverage") ?
                            -1:
                            o2.product.productType.equals("dessert") ? 0: 1;
                case "beverage":
                    return o2.product.productType.equals("beverage") ? 0: 1;
                default:
                    return 0;
            }
        });
        return command;
    }

    @PostMapping("/commands")
    @JsonView(CommandView.Index.class)
    public Command postCommand(@RequestBody Command command) {
        command.status = false;
        command.createdAt = LocalDateTime.now();
        return commandService.saveCommand(command);
    }

    @PostMapping("/commands/{idCommand}/products/{idProduct}")
    public void postCommandProduct(@PathVariable("idCommand") Long commandId, @PathVariable("idProduct") Long productId){
        commandService.addCommandProduct(commandId, productId);
    }

    @PutMapping("/commands/{idCommand}/products/{idProduct}")
    public void reduceCommandProduct(@PathVariable("idCommand") Long commandId, @PathVariable("idProduct") Long productId){
        commandService.reduceCommandProduct(commandId, productId);
    }

    @PutMapping("/commands/{id}/{productType}")
    public void askCommandProduct(@PathVariable("id") Long id, @PathVariable("productType") String productType) {
        commandService.askProductCategory(id, productType);
    }

    @DeleteMapping("/commands/{id}/pay")
    @JsonView({ServiceView.ShowCurrent.class})
    public Service closeCommand(@PathVariable("id") Long id) {
        commandService.closeCommand(id, "payé");
        return serviceController.getCurrentService();
    }
}
