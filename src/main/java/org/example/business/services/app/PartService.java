package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.PartDAO;
import org.example.infrastructure.database.entity.PartEntity;

import java.util.Optional;

@AllArgsConstructor
public class PartService {
    private final PartDAO partDAO;

    public PartEntity findPartBySerialNumber(String partSerialNumber){

        Optional<PartEntity> partBySerialNumber = partDAO.findPartBySerialNumber(partSerialNumber);
        if (partSerialNumber.isEmpty()){
            throw new RuntimeException();
        }

        return partBySerialNumber.get();
    }
}
