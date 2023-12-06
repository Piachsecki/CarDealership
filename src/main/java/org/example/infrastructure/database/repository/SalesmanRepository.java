package org.example.infrastructure.database.repository;

import org.example.business.dao.SalesmanDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.entity.SalesmanEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class SalesmanRepository implements SalesmanDAO {
    @Override
    public Optional<SalesmanEntity> findSalesmanByPesel(String pesel) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT sal FROM SalesmanEntity sal WHERE sal.pesel = :pesel";
            SalesmanEntity salesman = session.createQuery(query, SalesmanEntity.class)
                    .setParameter("pesel", pesel)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(salesman);
        }

    }
}
