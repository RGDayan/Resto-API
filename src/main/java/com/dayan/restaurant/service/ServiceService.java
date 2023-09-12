package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Service;
import com.dayan.restaurant.repository.ServiceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    public Service getMostRecentService() {
        List<Service> services = (List<Service>) serviceRepository.findAll();
        Service mostRecentService = null;
        if (services.isEmpty())
            return null;

        mostRecentService = services.get(services.size()-1);
        if (!mostRecentService.getStatus())
            return null;

        return mostRecentService;
    }

    public Iterable<Service> getServices(){
        return serviceRepository.findAll();
    }

    public void deleteService(final Long id){
        serviceRepository.deleteById(id);
    }

    public Service saveService(Service service){
        return serviceRepository.save(service);
    }
}