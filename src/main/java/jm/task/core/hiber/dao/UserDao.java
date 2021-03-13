package jm.task.core.hiber.dao;

import java.util.List;

import jm.task.core.hiber.model.User;

/**
 * UserDao
 */
public interface UserDao {

	void add(User user);

	List<User> listUsers();

	List<User> getCarOwner(String model, int series);

	User getUserById(Long id);

	void update(User user);

	void delete(User user);

}
