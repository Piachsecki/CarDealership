package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.MechanicDAO;
import org.example.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

@AllArgsConstructor
public class MechanicService {
    private final MechanicDAO mechanicDAO;


    public MechanicEntity findMechanicByPesel(String pesel){
        Optional<MechanicEntity> mechanic = mechanicDAO.findMechanicByPesel(pesel);
        if (mechanic.isEmpty()){
            throw new RuntimeException();
        }
        return mechanic.get();
    }
}
