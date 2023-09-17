package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.repository.CommandRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    public Optional<Command> getCommand(final Long id){
        return commandRepository.findById(id);
    }

    public Iterable<Command> getCommands(){
        return commandRepository.findAll();
    }

    public void deleteCommand(final Long id) {
        commandRepository.deleteById(id);
    }

    public Command saveCommand(Command command){
        return commandRepository.save(command);
    }

}
