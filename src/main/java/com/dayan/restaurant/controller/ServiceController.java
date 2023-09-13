package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Service;
import com.dayan.restaurant.service.CardService;
import com.dayan.restaurant.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CardService cardService;

    @GetMapping("/services")
    public Iterable<Service> getServices(){
        return serviceService.getServices();
    }

    @GetMapping("/service")
    public Service getCurrentService(){
        Service service = serviceService.getMostRecentService();
        if (service == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : Aucun service actif retrouvé");

        return service;
    }
}
