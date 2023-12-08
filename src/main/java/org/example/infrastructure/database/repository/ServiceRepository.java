package org.example.infrastructure.database.repository;

import org.example.business.dao.ServiceDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.ServiceEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class ServiceRepository implements ServiceDAO {
    @Override
    public Optional<ServiceEntity> findService(String serviceCode) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT serv FROM ServiceEntity serv WHERE serv.serviceCode = :serviceCode";
            ServiceEntity service = session.createQuery(query, ServiceEntity.class)
                    .setParameter("serviceCode", serviceCode)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(service);

        }
    }
}
