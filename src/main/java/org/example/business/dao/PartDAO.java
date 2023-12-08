package org.example.business.dao;

import org.example.infrastructure.database.entity.PartEntity;

import java.util.Optional;

public interface PartDAO {
    Optional<PartEntity> findPartBySerialNumber(String partSerialNumber);
}
