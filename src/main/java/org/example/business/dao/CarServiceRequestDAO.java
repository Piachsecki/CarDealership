package org.example.business.dao;

import org.example.infrastructure.database.entity.CarServiceRequestEntity;
import org.example.infrastructure.database.entity.ServicePartEntity;

import java.util.Optional;

public interface CarServiceRequestDAO {
    Optional<CarServiceRequestEntity> findServiceRequest(String carServiceRequestNumber);
    ServicePartEntity issueServicePart(ServicePartEntity servicePartEntity);

    void updateServiceRequestToDone(String carServiceRequestNumber);
}
