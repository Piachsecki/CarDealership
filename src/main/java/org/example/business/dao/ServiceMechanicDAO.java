package org.example.business.dao;

import org.example.infrastructure.database.entity.ServiceMechanicEntity;

public interface ServiceMechanicDAO {
    ServiceMechanicEntity issueServiceMechanic(ServiceMechanicEntity serviceMechanicEntity);
}
