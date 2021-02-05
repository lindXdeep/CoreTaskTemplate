package jm.task.core.hiber.service;

import org.springframework.stereotype.Service;

import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.model.User;
import lombok.Getter;
import lombok.Setter;

@Service
public class UserBuilder{

    @Getter @Setter String firstName;
    @Getter @Setter String lastName;
    @Getter @Setter String email;
    @Getter @Setter Car car;
    
    public UserBuilder firstName(final String firstName){
        this.firstName = firstName;
        return this;
    }
    public UserBuilder lastName(final String lastName){
        this.lastName = lastName;
        return this;
    }
    public UserBuilder email(final String email){
        this.email = email;
        return this;
    }
    public UserBuilder car(final Car car){
        this.car = car;
        return this;
    }

    public User build(){
        return new User(this);
    }
}
