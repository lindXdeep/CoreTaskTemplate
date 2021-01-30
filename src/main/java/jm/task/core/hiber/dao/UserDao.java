package jm.task.core.hiber.dao;

import jm.task.core.hiber.model.User;

import java.util.List;
public interface UserDao {
    void add(User user);
    List<User> listUsers();
}
