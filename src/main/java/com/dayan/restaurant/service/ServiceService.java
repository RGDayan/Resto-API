package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Service;
import com.dayan.restaurant.repository.ServiceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public Optional<Service> getService(final Long id) {
        return serviceRepository.findById(id);
    }

    public Service getLatestService() {
        List<Service> services = (List<Service>) serviceRepository.findAll();
        if (services.isEmpty())
            return null;

        return services.get(services.size()-1);
    }

    public Service getCurrentService() {
        List<Service> services = (List<Service>)serviceRepository.findAll();
        if (services.isEmpty())
            return null;

        List<Service> openedService = services.stream().filter((x) -> x.status).toList();
        if (openedService.size() > 1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : Plusieurs services sont ouverts");
        if (openedService.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO : Aucun service n'est ouvert");

        return openedService.get(0);
    }

    public Iterable<Service> getServices(){
        return serviceRepository.findAll();
    }

    public void deleteService(final Long id){
        serviceRepository.deleteById(id);
    }

    public Service saveService(Service service){
        List<Service> services = (List<Service>)serviceRepository.findAll();
        List<Service> openedService = services.stream().filter((x) -> x.status).toList();
        if (openedService.isEmpty())
            return serviceRepository.save(service);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : Un ou plusieurs services sont déjà ouverts. Clôturez-les d'abord pour en ouvrir un nouveau");
    }

    public Service closeService(Long id) {
        Service service = serviceRepository.findById(id).orElse(null);
        if (service == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERREUR : service non trouvé");
        if (!service.status)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "INFO : service déjà fermé");

        if (service.openedDate.isAfter(LocalDateTime.now()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ATTENTION : tentative de fermeture du service avant son heure d'ouverture");

        service.closedDate = LocalDateTime.now();
        service.status = false;
        return serviceRepository.save(service);
    }
}