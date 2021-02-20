package jm.task.core.hiber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jm.task.core.hiber.dao.CarDao;
import jm.task.core.hiber.model.Car;

/**
 * CarServiceImpl
 */
@Service
@Transactional(readOnly = true)
public class CarserviceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> listCars() {
       
        return carDao.listCars();
    }

    @Override
    public Car getCarbyId(Long id) {
    
        return carDao.getCarById(id);
    }

    @Override
    @Transactional
    public void add(Car car) {
       carDao.add(car);
    }

    @Override
    @Transactional
    public void update(Car car) {
        carDao.update(car);
    }
}
