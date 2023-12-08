package org.example.infrastructure.database.repository;

import org.example.business.dao.management.CarDealershipManagementDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class CarDealershipManagementRepository implements CarDealershipManagementDAO {

    @Override
    public void purge() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.createMutationQuery("DELETE from ServiceMechanicEntity").executeUpdate();
            session.createMutationQuery("DELETE from ServicePartEntity").executeUpdate();
            session.createMutationQuery("DELETE from CarServiceRequestEntity").executeUpdate();
            session.createMutationQuery("DELETE from InvoiceEntity").executeUpdate();
            session.createMutationQuery("DELETE from MechanicEntity").executeUpdate();
            session.createMutationQuery("DELETE from PartEntity").executeUpdate();
            session.createMutationQuery("DELETE from ServiceEntity").executeUpdate();
            session.createMutationQuery("DELETE from CarToBuyEntity").executeUpdate();
            session.createMutationQuery("DELETE from CarToServiceEntity").executeUpdate();
            session.createMutationQuery("DELETE from CustomerEntity").executeUpdate();
            session.createMutationQuery("DELETE from AddressEntity").executeUpdate();
            session.createMutationQuery("DELETE from SalesmanEntity").executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void saveAll(List<?> entities) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            for (var entity : entities) {
                session.persist(entity);
            }
            session.getTransaction().commit();
        }
    }
}
