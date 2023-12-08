package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.CarServiceRequestDAO;
import org.example.infrastructure.database.entity.CarServiceRequestEntity;
import org.example.infrastructure.database.entity.CarToServiceEntity;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.entity.ServicePartEntity;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class CarServiceRequestService {
    private CustomerService customerService;
    private CarService carService;
    private CarServiceRequestDAO carServiceRequestDAO;

    public void request(String email, String carsVin, String customerComment) {
        CustomerEntity customer = customerService.findCustomer(email);
        CarToServiceEntity carToService = carService.findCarToService(carsVin);
        CarServiceRequestEntity carServiceRequest = buildCarServiceRequest(customer, carToService, customerComment);
        customer.getCarServiceRequests().add(carServiceRequest);
        customerService.issueServiceRequest(customer);
    }

    private CarServiceRequestEntity buildCarServiceRequest(CustomerEntity customer, CarToServiceEntity car, String customerComment) {
        return CarServiceRequestEntity.builder()
                .carServiceRequestNumber(UUID.randomUUID().toString())
                .receivedDateTime(OffsetDateTime.now())
                .completedDateTime(null)
                .customerComment(customerComment)
                .customer(customer)
                .carToService(car)
                .build();
    }

    public CarServiceRequestEntity findServiceRequest(String carServiceRequestNumber) {

        Optional<CarServiceRequestEntity> serviceRequest
                = carServiceRequestDAO.findServiceRequest(carServiceRequestNumber);
        if (serviceRequest.isEmpty()){
            throw new RuntimeException();
        }
        return serviceRequest.get();
    }

    public void issueServicePart(ServicePartEntity servicePartEntity) {
        carServiceRequestDAO.issueServicePart(servicePartEntity);
    }

    public void updateServiceRequestToDone(String carServiceRequestNumber) {
        carServiceRequestDAO.updateServiceRequestToDone(carServiceRequestNumber);
    }
}
