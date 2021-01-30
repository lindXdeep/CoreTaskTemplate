package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
public interface UserDao {
    void add(User user);
    List<User> listUsers();
}
