package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.CarDAO;
import org.example.infrastructure.database.entity.CarToBuyEntity;

import java.util.Optional;

@AllArgsConstructor
public class CarService {
    private final CarDAO carDAO;
    public CarToBuyEntity findCarToBuy(String vin) {
        Optional<CarToBuyEntity> car = carDAO.findCarToBuyByVin(vin);
        if (car.isEmpty()){
            throw new RuntimeException("There is not a car like this in the database!");
        }
        return car.get();
    }
}
