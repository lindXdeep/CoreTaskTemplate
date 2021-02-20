package jm.task.core.hiber.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import jm.task.core.hiber.model.User;

/**
 * UserDaoJpaImpl
 */
@Repository
public class UserDaoJpaImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        
        return entityManager.createQuery(

                "select u from User u", User.class

        ).getResultList();
    }

    @Override
    public List<User> getCarOwner(String model, int series) {
        
        TypedQuery<User> query = entityManager.createQuery(

            "select u from User u where u.car.model =: model and u.car.series =: series", User.class
        );
        
        return query.setParameter("model", model)
                    .setParameter("series", series)
                    .getResultList();
    }

    @Override
    public User getUserById(Long id) {

        TypedQuery<User> query = entityManager.createQuery(

                "select u from User u where u.id =: id ", User.class
        );
        
        return query.setParameter("id", id)
                    .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {

        entityManager.remove(

            getUserById(user.getId())
        );
    }
}