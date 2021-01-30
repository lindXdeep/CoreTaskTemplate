package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;
public interface UserService {
    void add(User user);
    List<User> listUsers();
}
