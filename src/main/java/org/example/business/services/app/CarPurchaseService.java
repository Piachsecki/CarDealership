package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.services.init.FileDataPreparationService;
import org.example.infrastructure.database.entity.CarToBuyEntity;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.entity.InvoiceEntity;
import org.example.infrastructure.database.entity.SalesmanEntity;

import java.time.OffsetDateTime;
import java.util.UUID;


@AllArgsConstructor
public class CarPurchaseService {
    private FileDataPreparationService fileDataPreparationService;
    private CustomerService customerService;
    private SalesmanService salesmanService;
    private CarService carService;

    public void purchase(String email, String vin, String salesmanPesel) {
        CarToBuyEntity carToBuy = carService.findCarToBuy(vin);
        SalesmanEntity salesman = salesmanService.findSalesman(salesmanPesel);
        CustomerEntity customer = customerService.findCustomer(email);
        InvoiceEntity invoice = buildInvoice(salesman, carToBuy);


        customer.getInvoices().add(invoice);
        customerService.issueInvoice(customer);
    }

    private InvoiceEntity buildInvoice(SalesmanEntity salesman, CarToBuyEntity carToBuy) {
        return InvoiceEntity.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.now())
                .salesman(salesman)
                .carToBuy(carToBuy)
                .build();
    }


}
