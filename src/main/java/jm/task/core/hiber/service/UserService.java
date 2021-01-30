package jm.task.core.hiber.service;

import jm.task.core.hiber.model.User;

import java.util.List;
public interface UserService {
    void add(User user);
    List<User> listUsers();
}
