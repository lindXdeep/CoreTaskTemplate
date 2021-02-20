package jm.task.core.hiber.service;

import org.springframework.stereotype.Service;

import jm.task.core.hiber.model.Car;
import lombok.Data;
@Data
@Service
public class CarBuilder {

    private String model;
    private Integer series;

    public CarBuilder model(final String model){
        this.model = model;
        return this;
    }
    public CarBuilder series(final int series){
        this.series = series;
        return this;
    }

    public Car build(){
        return new Car(this);
    }
}
