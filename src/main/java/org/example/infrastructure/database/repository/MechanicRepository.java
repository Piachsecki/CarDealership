package org.example.infrastructure.database.repository;

import org.example.business.dao.MechanicDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.entity.MechanicEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class MechanicRepository implements MechanicDAO {
    @Override
    public Optional<MechanicEntity> findMechanicByPesel(String pesel) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT mech FROM MechanicEntity mech WHERE mech.pesel = :pesel";
            MechanicEntity mechanic = session.createQuery(query, MechanicEntity.class)
                    .setParameter("pesel", pesel)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(mechanic);
        }
    }
}
