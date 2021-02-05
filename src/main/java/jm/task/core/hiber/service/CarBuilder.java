package jm.task.core.hiber.service;

import org.springframework.stereotype.Service;

import jm.task.core.hiber.model.Car;
import lombok.Getter;
import lombok.Setter;

@Service
public class CarBuilder {

    @Getter @Setter private String model;
    @Getter @Setter private Integer series;

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
