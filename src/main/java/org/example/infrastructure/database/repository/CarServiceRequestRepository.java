package org.example.infrastructure.database.repository;

import org.example.business.dao.CarServiceRequestDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CarServiceRequestEntity;
import org.example.infrastructure.database.entity.ServicePartEntity;
import org.hibernate.Session;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

public class CarServiceRequestRepository implements CarServiceRequestDAO {
    @Override
    public Optional<CarServiceRequestEntity> findServiceRequest(String carServiceRequestNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT req FROM CarServiceRequestEntity req WHERE req.carServiceRequestNumber = :carServiceRequestNumber";
            CarServiceRequestEntity carServiceRequest = session.createQuery(query, CarServiceRequestEntity.class)
                    .setParameter("carServiceRequestNumber", carServiceRequestNumber)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(carServiceRequest);
        }
    }

    @Override
    public ServicePartEntity issueServicePart(ServicePartEntity servicePartEntity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(servicePartEntity);
            session.getTransaction().commit();
            return servicePartEntity;
        }

    }

    @Override
    public void updateServiceRequestToDone(String carServiceRequestNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT req FROM CarServiceRequestEntity req WHERE req.carServiceRequestNumber =:carServiceRequestNumber";
            CarServiceRequestEntity carServiceRequest = session.createQuery(query, CarServiceRequestEntity.class)
                    .setParameter("carServiceRequestNumber", carServiceRequestNumber)
                    .getSingleResult();
            carServiceRequest.setCompletedDateTime(OffsetDateTime.now());

            session.merge(carServiceRequest);
            session.getTransaction().commit();
        }
    }
}
