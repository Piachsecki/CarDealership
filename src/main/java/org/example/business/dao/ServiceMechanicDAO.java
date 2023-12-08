package org.example.business.dao;

import org.example.business.services.app.ServiceMechanicService;
import org.example.infrastructure.database.entity.ServiceMechanicEntity;

public interface ServiceMechanicDAO {
    ServiceMechanicEntity issueServiceMechanic(ServiceMechanicEntity serviceMechanicEntity);
}
