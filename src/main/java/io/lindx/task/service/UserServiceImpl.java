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
import io.lindx.task.dao.UserRepository;
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
// @Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Autowired
  private UserRepository userRepo;

	@Override
//	@Transactional
	public void add(User user) {
<<<<<<< HEAD

    repository.save(user);
=======
	//	userDao.add(user);
    userRepo.save(user);
>>>>>>> add repository save mithod
	}

	@Override
	public List<User> listUsers() {

    return repository.findAll();
	}

	@Override
	public User getUserById(Long id) {

    return repository.getOne(id);
	}

	@Override
//	@Transactional
	public void update(User user) {

    repository.setUserInfoById(

        user.getFirstName(), 
        user.getLastName(), 
        user.getEmail(), 
        user.getPassword(), 
        user.getId()
    );
	}

	@Override
	// @Transactional
	public void delete(User user) {

		repository.delete(user);
	}

	@Override
	public User getUserByEmail(String email) {

    return repository.findByEmail(email);
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