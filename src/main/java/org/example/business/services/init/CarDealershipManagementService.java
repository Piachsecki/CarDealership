package org.example.business.services.init;

import lombok.AllArgsConstructor;
import org.example.business.dao.management.CarDealershipManagementDAO;
import org.example.infrastructure.database.repository.CarDealershipManagementRepository;

import java.util.List;

@AllArgsConstructor
public class CarDealershipManagementService{
    private CarDealershipManagementDAO carDealershipManagementRepository;
    private FileDataPreparationService fileDataPreparationService;

    public void purge() {
        carDealershipManagementRepository.purge();
    }

    public void init() {
        List<?> entities = fileDataPreparationService.prepareInitData();
        carDealershipManagementRepository.saveAll(entities);
    }
}
