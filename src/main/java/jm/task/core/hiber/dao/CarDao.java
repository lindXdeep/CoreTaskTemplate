package jm.task.core.hiber.dao;

import java.util.List;

import jm.task.core.hiber.model.Car;

/**
 * CarDao
 */
public interface CarDao {

    void add(Car car);

    List<Car> listCars();
}
