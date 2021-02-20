package jm.task.core.hiber.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jm.task.core.hiber.model.Car;

//@Repository
public class CarDaoImpl implements CarDao {

    //@Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

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

    @Override
    public void update(Car car) {

        Query query = getSession().createQuery("UPDATE Car SET model =: model, series =: series WHERE id =: id ");
            query.setParameter("model", car.getModel());
            query.setParameter("series", car.getSeries());
            query.setParameter("id", car.getId());

        query.executeUpdate();
    }
}
