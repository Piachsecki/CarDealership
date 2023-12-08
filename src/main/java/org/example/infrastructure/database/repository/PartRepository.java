package org.example.infrastructure.database.repository;

import org.example.business.dao.PartDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.MechanicEntity;
import org.example.infrastructure.database.entity.PartEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class PartRepository implements PartDAO {
    @Override
    public Optional<PartEntity> findPartBySerialNumber(String partSerialNumber) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT parts FROM PartEntity parts WHERE parts.serialNumber = :partSerialNumber";
            PartEntity part = session.createQuery(query, PartEntity.class)
                    .setParameter("partSerialNumber", partSerialNumber)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(part);
        }
    }
}
