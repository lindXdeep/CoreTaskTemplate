package jm.task.core.hiber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jm.task.core.hiber.dao.UserDao;
import jm.task.core.hiber.model.User;

/**
 * UserServiceImpl
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