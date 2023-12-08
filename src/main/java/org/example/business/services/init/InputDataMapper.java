package org.example.business.services.init;

import lombok.experimental.UtilityClass;
import org.example.infrastructure.database.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InputDataMapper {

    public static List<SalesmanEntity> mapSalesmen(List<String> salesmenLines) {
        List<SalesmanEntity> result = new ArrayList<>();
        for (String salesmenLine : salesmenLines) {
            List<String> data = DataManipulationUtil.dataAsList(salesmenLine);
            result.add(
                    SalesmanEntity.builder()
                            .name(data.get(0))
                            .surname(data.get(1))
                            .pesel(data.get(2))
                            .build());
        }
        return result;
    }

    public static List<MechanicEntity> mapMechanic(List<String> mechanicLines) {
        List<MechanicEntity> result = new ArrayList<>();
        for (String mechanicLine : mechanicLines) {
            List<String> data = DataManipulationUtil.dataAsList(mechanicLine);
            result.add(
                    MechanicEntity.builder()
                            .name(data.get(0))
                            .surname(data.get(1))
                            .pesel(data.get(2))
                            .build());
        }
        return result;
    }


    public static List<CarToBuyEntity> mapCarToBuy(List<String> carToBuyLines) {
        List<CarToBuyEntity> result = new ArrayList<>();
        for (String carToBuyLine : carToBuyLines) {
            List<String> data = DataManipulationUtil.dataAsList(carToBuyLine);
            result.add(
                    CarToBuyEntity.builder()
                            .vin(data.get(0))
                            .brand(data.get(1))
                            .model(data.get(2))
                            .year(Short.parseShort(data.get(3)))
                            .color(data.get(4))
                            .price(new BigDecimal(data.get(5)))
                            .build());
        }
        return result;
    }

    public static List<PartEntity> mapPart(List<String> mapPartLines) {
        List<PartEntity> result = new ArrayList<>();
        for (String mapPartLine : mapPartLines) {
            List<String> data = DataManipulationUtil.dataAsList(mapPartLine);
            result.add(
                    PartEntity.builder()
                            .serialNumber(data.get(0))
                            .description(data.get(1))
                            .price(new BigDecimal(data.get(2)))
                            .build());
        }
        return result;
    }


    public static List<ServiceEntity> mapService(List<String> mapServiceLines) {
        List<ServiceEntity> result = new ArrayList<>();
        for (String mapServiceLine : mapServiceLines) {
            List<String> data = DataManipulationUtil.dataAsList(mapServiceLine);
            result.add(
                    ServiceEntity.builder()
                            .serviceCode(data.get(0))
                            .description(data.get(1))
                            .price(new BigDecimal(data.get(2)))
                            .build());
        }
        return result;
    }


}
