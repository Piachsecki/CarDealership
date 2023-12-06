package org.example.infrastructure.database.repository;

import org.example.business.dao.CustomerDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.entity.InvoiceEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class CustomerRepository implements CustomerDAO {
    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(customer);
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
            Set<InvoiceEntity> invoices = customer.getInvoices();
            for (InvoiceEntity invoice : invoices) {
                invoice.setCustomer(customer);
                session.persist(invoice);
            }
//
//            customer.getInvoices().forEach(
//                    invoiceEntity -> {
//                        invoiceEntity.setCustomer(customer);
//                        session.persist(invoiceEntity);
//                    }
//            );
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
}
