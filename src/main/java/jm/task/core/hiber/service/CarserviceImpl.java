package jm.task.core.hiber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jm.task.core.hiber.dao.CarDao;
import jm.task.core.hiber.model.Car;

/**
 * CarServiceImpl
 */
@Service
public class CarserviceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
       
        return carDao.listCars();
    }
}
