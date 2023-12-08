package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.infrastructure.database.entity.CarServiceRequestEntity;
import org.example.infrastructure.database.entity.CarToServiceEntity;
import org.example.infrastructure.database.entity.CustomerEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
public class CarServiceRequestService {
    private CustomerService customerService;
    private CarService carService;

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
}
