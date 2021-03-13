package io.lindx.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.lindx.task.dao.UserDao;
import io.lindx.task.model.User;

/**
 * Implementation for {@link UserService}.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

}