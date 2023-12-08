package org.example.infrastructure.database.repository;

import org.example.business.dao.CarDAO;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.CarToBuyEntity;
import org.example.infrastructure.database.entity.CarToServiceEntity;
import org.example.infrastructure.database.entity.SalesmanEntity;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;

public class CarRepository implements CarDAO {
    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
                if (Objects.isNull(session)) {
                    throw new RuntimeException("Session is null");
                }
                session.beginTransaction();
                String query = "SELECT cars FROM CarToBuyEntity cars WHERE cars.vin = :vin";
            CarToBuyEntity car = session.createQuery(query, CarToBuyEntity.class)
                    .setParameter("vin", vin)
                    .getSingleResult();
            session.getTransaction().commit();
                return Optional.of(car);

        }
    }

    @Override
    public CarToServiceEntity saveCarToService(CarToServiceEntity car) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(car);
            session.getTransaction().commit();
            return car;
        }
    }

    @Override
    public Optional<CarToServiceEntity> findCarToServiceByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT cars FROM CarToServiceEntity cars WHERE cars.vin = :vin";
            CarToServiceEntity car = session.createQuery(query, CarToServiceEntity.class)
                    .setParameter("vin", vin)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(car);

        }
    }
}
