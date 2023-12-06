package org.example.business.services.init;

import org.example.infrastructure.database.entity.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.business.services.init.Keys.Entity.*;
import static org.example.business.services.init.Keys.InputDataGroup.INIT;


public class FileDataPreparationService {
    public List<?> prepareInitData() {
        List<String> salesmenLines = InputDataCache.getInputData(INIT, SALESMAN);
        List<SalesmanEntity> salesmanEntities = InputDataMapper.mapSalesmen(salesmenLines);

        List<String> mechanicsLines = InputDataCache.getInputData(INIT, MECHANIC);
        List<MechanicEntity> mechanicEntities = InputDataMapper.mapMechanic(mechanicsLines);

        List<String> partsLines = InputDataCache.getInputData(INIT, PART);
        List<PartEntity> partEntities = InputDataMapper.mapPart(partsLines);

        List<String> servicesLines = InputDataCache.getInputData(INIT, SERVICE);
        List<ServiceEntity> serviceEntities = InputDataMapper.mapService(servicesLines);

        List<String> carToBuyLines = InputDataCache.getInputData(INIT, CAR);
        List<CarToBuyEntity> carToBuyEntities = InputDataMapper.mapCarToBuy(carToBuyLines);


        return Stream.of(salesmanEntities, mechanicEntities, serviceEntities, carToBuyEntities, partEntities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
