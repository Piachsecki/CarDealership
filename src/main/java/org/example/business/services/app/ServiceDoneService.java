package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.ServiceDAO;
import org.example.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

@AllArgsConstructor
public class ServiceDoneService {
    private ServiceDAO serviceDAO;

    public ServiceEntity findService(String serviceCode){
        Optional<ServiceEntity> service = serviceDAO.findService(serviceCode);
        if(service.isEmpty()){
            throw new RuntimeException();
        }
        return service.get();
    }
}
