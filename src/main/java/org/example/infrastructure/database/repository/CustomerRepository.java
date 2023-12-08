package org.example.infrastructure.database.repository;

import org.example.business.dao.CustomerDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDAO {
    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            if (Objects.isNull(customer.getCustomerId())) {
                session.persist(customer);
            }
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public void issueInvoiceForCustomer(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();


            customer.getInvoices()
                    .stream()
                    .filter(invoiceEntity -> Objects.isNull(invoiceEntity.getInvoiceId()))
                    .forEach(
                            invoiceEntity -> {
                                invoiceEntity.setCustomer(customer);
                                session.persist(invoiceEntity);
                            }
                    );
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomerByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT cus FROM CustomerEntity cus WHERE cus.email = :email";
            CustomerEntity customer = session.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(customer);
        }
    }

    @Override
    public void issueServiceRequest(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            customer.getCarServiceRequests().stream()
                    .filter(serviceRequest -> Objects.isNull(serviceRequest.getCarServiceRequestId()))
                    .forEach(session::persist);
            session.getTransaction().commit();

        }
    }
}
