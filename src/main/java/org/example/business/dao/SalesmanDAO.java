package org.example.business.dao;

import org.example.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDAO {
    Optional<SalesmanEntity> findSalesmanByPesel(String pesel);
}
