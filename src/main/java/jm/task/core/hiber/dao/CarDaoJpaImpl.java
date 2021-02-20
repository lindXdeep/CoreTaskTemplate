package jm.task.core.hiber.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import jm.task.core.hiber.model.Car;

@Repository
public class CarDaoJpaImpl implements CarDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void add(Car car) {
        entityManager.persist(car);
    }

    @Override
    public List<Car> listCars() {
        
        return entityManager.createQuery(

                "select c from Car c", Car.class

        ).getResultList();
    }

    @Override
    public Car getCarById(Long id) {
        
        TypedQuery<Car> query = entityManager.createQuery(
                "selset c from Car c where c.id =: id", Car.class   
        );

        return query.setParameter("id", id).getResultList()
                .stream().findAny().orElse(null);
    }

    @Override
    public void update(Car car) {
       
        entityManager.merge(car);
    }
}