package org.example.business.dao;

import org.example.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

public interface MechanicDAO {
    Optional<MechanicEntity> findMechanicByPesel(String pesel);
}
