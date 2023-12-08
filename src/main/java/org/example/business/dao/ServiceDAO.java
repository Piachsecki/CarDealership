package org.example.business.dao;

import org.example.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

public interface ServiceDAO {
    Optional<ServiceEntity> findService(String serviceCode);
}
