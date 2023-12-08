package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.infrastructure.database.entity.*;

@AllArgsConstructor
public class ProcessRequestService {
    private PartService partService;
    private CarServiceRequestService carServiceRequestService;
    private MechanicService mechanicService;
    private ServiceDoneService serviceDone;
    private ServiceMechanicService serviceMechanicService;


    public void processServicePart(String carServiceRequestNumber,
                                   String partSerialNumber,
                                   int quantity
    ) {
        PartEntity part = partService.findPartBySerialNumber(partSerialNumber);
        CarServiceRequestEntity serviceRequest = carServiceRequestService.findServiceRequest(carServiceRequestNumber);
        ServicePartEntity servicePartEntity = partServiceBuilder(serviceRequest, quantity, part);
        serviceRequest.getServiceParts().add(servicePartEntity);
        carServiceRequestService.issueServicePart(servicePartEntity);

    }

    private ServicePartEntity partServiceBuilder(
            CarServiceRequestEntity serviceRequest,
            int quantity,
            PartEntity part
    ) {
        return ServicePartEntity.builder()
                .part(part)
                .quantity(quantity)
                .serviceRequest(serviceRequest)
                .build();
    }

    public void processRequestService(
            String carServiceRequestNumber,
            String pesel,
            String serviceCode,
            String comment,
            int hoursSpent
    ) {
        CarServiceRequestEntity serviceRequest = carServiceRequestService.findServiceRequest(carServiceRequestNumber);
        MechanicEntity mechanic = mechanicService.findMechanicByPesel(pesel);
        ServiceEntity service = serviceDone.findService(serviceCode);
        ServiceMechanicEntity serviceMechanicEntity = buildServiceMechanicEntity(service, mechanic, comment,hoursSpent, serviceRequest );
        serviceRequest.getServiceMechanics().add(serviceMechanicEntity);
        serviceMechanicService.issueServiceMechanic(serviceMechanicEntity);
        carServiceRequestService.updateServiceRequestToDone(serviceRequest.getCarServiceRequestNumber());

    }

    private ServiceMechanicEntity buildServiceMechanicEntity(ServiceEntity service, MechanicEntity mechanic, String comment, int hoursSpent, CarServiceRequestEntity serviceRequest) {
        return ServiceMechanicEntity.builder()
                .hours(hoursSpent)
                .comment(comment)
                .service(service)
                .mechanic(mechanic)
                .serviceRequest(serviceRequest)
                .build();
    }


}
