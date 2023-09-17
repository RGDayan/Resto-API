package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.service.CommandService;
import com.dayan.restaurant.view.CommandView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {
    @Autowired
    private CommandService commandService;

    @GetMapping("/commands")
    @JsonView(CommandView.Index.class)
    public Iterable<Command> getCommands(){
        return commandService.getCommands();
    }
}
