package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.CustomerDAO;
import org.example.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

@AllArgsConstructor
public class CustomerService {
    private final CustomerDAO customerDAO;
    public void issueInvoice(CustomerEntity customerEntity) {
        customerDAO.issueInvoiceForCustomer(customerEntity);
    }

    public CustomerEntity findCustomer(String email) {
        Optional<CustomerEntity> customer = customerDAO.findCustomerByEmail(email);
        if (customer.isEmpty()) {
            throw new RuntimeException("Could not find customer by email: [%s]".formatted(email));
        }
        return customer.get();

    }

    public CustomerEntity saveCustomer(CustomerEntity customer){
        return customerDAO.saveCustomer(customer);
    }

    public void issueServiceRequest(CustomerEntity customer) {
        customerDAO.issueServiceRequest(customer);
    }


}
