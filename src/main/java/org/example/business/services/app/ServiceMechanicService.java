package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.ServiceMechanicDAO;
import org.example.infrastructure.database.entity.ServiceMechanicEntity;
@AllArgsConstructor
public class ServiceMechanicService {
    private ServiceMechanicDAO serviceMechanicDAO;

    public ServiceMechanicEntity issueServiceMechanic(ServiceMechanicEntity serviceMechanicEntity){
        return serviceMechanicDAO.issueServiceMechanic(serviceMechanicEntity);
    }
}
