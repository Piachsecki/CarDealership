package org.example.infrastructure.database.repository;

import org.example.business.dao.ServiceMechanicDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.ServiceMechanicEntity;
import org.hibernate.Session;

import java.util.Objects;

public class ServiceMechanicRepository implements ServiceMechanicDAO {
    @Override
    public ServiceMechanicEntity issueServiceMechanic(ServiceMechanicEntity serviceMechanicEntity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            if (Objects.isNull(serviceMechanicEntity.getServiceMechanicId())) {
                session.persist(serviceMechanicEntity);
            }
            session.getTransaction().commit();
            return serviceMechanicEntity;
        }
    }
}
