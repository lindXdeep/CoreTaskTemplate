package jm.task.core.hiber.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jm.task.core.hiber.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {

        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public Car getCarById(Long id) {

        return sessionFactory.getCurrentSession().get(Car.class, id);
    }
}
