package io.lindx.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.lindx.task.dao.UserDao;
import io.lindx.task.model.User;
import io.lindx.task.repository.UserRepository;

/**
 * Implementation for {@link UserService}.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

  @Autowired
  private UserRepository userRepo;

	@Override
	@Transactional
	public void add(User user) {

	  // userDao.add(user);

    userRepo.save(user);
	}

	@Override
	public List<User> listUsers() {

		// return userDao.listUsers();

    return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {

		//return userDao.getUserById(id);

    return userRepo.findById(id).stream().findAny().orElse(null);
	}

	@Override
	@Transactional
	public void update(User user) {
		
    //userDao.update(user);

    userRepo.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {

		//userDao.delete(user);

    userRepo.delete(user);
	}

	@Override
	public User getUserByEmail(String email) {

		//return userDao.getUserByEmail(email);
    
    return userRepo.findByEmail(email);
	}

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
    User user = getUserByEmail(email);

    if(user == null){
      throw new UsernameNotFoundException("user not found");
    }
    
    return user;
  }
}