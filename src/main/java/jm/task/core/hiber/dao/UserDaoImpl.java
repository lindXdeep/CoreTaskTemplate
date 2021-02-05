package jm.task.core.hiber.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jm.task.core.hiber.model.User;

/**
 * UserDaoImpl
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(User user) {
        getSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        TypedQuery<User> query = getSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getCarOwner(String model, int series) {

        TypedQuery<User> query = getSession().createQuery("from User where car.model =: model and car.series =: series");
                query.setParameter("model", model);
                query.setParameter("series", series);

        return query.getResultList();
    }
}