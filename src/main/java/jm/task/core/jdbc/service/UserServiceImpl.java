package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
 
    @Transactional
    @Override
    public void add(User user) {
       userDao.add(user);
    }
 
    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
       return userDao.listUsers();
    }
}
