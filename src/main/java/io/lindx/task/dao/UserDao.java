package io.lindx.task.dao;

import java.util.List;

import io.lindx.task.model.User;

/**
 * UserDao.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
public interface UserDao {

	void add(User user);

	User getUserById(Long id);

	void update(User user);

	void delete(User user);

	List<User> listUsers();

}
