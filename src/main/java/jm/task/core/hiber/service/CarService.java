package jm.task.core.hiber.service;

import java.util.List;

import jm.task.core.hiber.model.Car;

/**
 * CarService
 */
public interface CarService {

    List<Car> listCars();
    Car getCarbyId(Long id);
    void add(Car car);
}
