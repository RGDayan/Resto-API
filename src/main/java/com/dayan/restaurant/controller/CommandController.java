package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.service.CommandService;
import com.dayan.restaurant.view.CommandView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class CommandController {
    @Autowired
    private CommandService commandService;

    @GetMapping("/commands")
    @JsonView(CommandView.Index.class)
    public Iterable<Command> getCommands(){
        return commandService.getCommands();
    }

    @GetMapping("/commands/{id}")
    @JsonView(CommandView.Index.class)
    public Command getCommand(@PathVariable("id") Long id) {
        return commandService.getCommand(id);
    }

    @PostMapping("/commands")
    @JsonView(CommandView.Index.class)
    public Command postCommand(@RequestBody Command command) {
        command.status = false;
        command.createdAt = LocalDateTime.now();
        return commandService.saveCommand(command);
    }

    @PostMapping("/commands/{idCommand}/products")
    @JsonView({CommandView.Index.class})
    public Command postCommandProduct(@PathVariable("idCommand") Long id, @RequestBody Product product){
        Command command = commandService.addCommandProduct(id, product);
        return command;
    }
}
