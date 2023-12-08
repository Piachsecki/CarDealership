package org.example.business.dao;

import org.example.infrastructure.database.entity.CarToBuyEntity;
import org.example.infrastructure.database.entity.CarToServiceEntity;

import java.util.Optional;

public interface CarDAO {
    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);
    CarToServiceEntity saveCarToService(CarToServiceEntity car);

    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);
}
