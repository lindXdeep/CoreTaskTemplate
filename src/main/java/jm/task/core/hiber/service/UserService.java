package jm.task.core.hiber.service;

import java.util.List;

import jm.task.core.hiber.model.User;

/**
 * UserService
 */
public interface UserService {

    void add(User user);

    List<User> listUsers();

    List<User> getCarOwner(String model, int series);
}