package org.example.business.dao;

import org.example.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {
    CustomerEntity saveCustomer(CustomerEntity customer);

    void issueInvoiceForCustomer(CustomerEntity customer);

    Optional<CustomerEntity> findCustomerByEmail(String email);
}
