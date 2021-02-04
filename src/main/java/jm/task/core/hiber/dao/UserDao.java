package jm.task.core.hiber.dao;

import java.util.List;

import jm.task.core.hiber.model.User;
/**
 * UserDao
 */
public interface UserDao {

    void add(User user);

    List<User> listUsers();
}

