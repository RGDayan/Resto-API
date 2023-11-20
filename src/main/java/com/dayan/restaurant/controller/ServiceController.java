package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Service;
import com.dayan.restaurant.service.CardService;
import com.dayan.restaurant.service.ServiceService;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CardService cardService;

    @GetMapping("/services")
    @JsonView(ServiceView.Index.class)
    public Iterable<Service> getServices(){
        List<Service> services = (List<Service>)serviceService.getServices();
        if (services.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : aucun service trouvé");

        return services;
    }

    @GetMapping("/services/latest")
    @JsonView(ServiceView.Index.class)
    public Service getLatestService(){
        Service service = serviceService.getLatestService();
        if (service == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : Aucun service actif retrouvé");

        return service;
    }

    @GetMapping("/services/current")
    @JsonView(ServiceView.ShowCurrent.class)
    public Service getCurrentService(){
        Service service = serviceService.getCurrentService();
        if (service == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : Aucun service actif retrouvé");

        return service;
    }

    @PostMapping("/services")
    @JsonView(ServiceView.Index.class)
    public Service postService(@RequestBody Service service){
        return serviceService.saveService(service);
    }

    @DeleteMapping("/services/{id}")
    @JsonView(ServiceView.Index.class)
    public Service deleteService(@PathVariable("id") Long id){
        return serviceService.closeService(id);
    }
}
