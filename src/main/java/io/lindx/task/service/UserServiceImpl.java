package io.lindx.task.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import io.lindx.task.dao.UserDao;
import io.lindx.task.dao.UserRepository;
=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
import io.lindx.task.model.User;
import io.lindx.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for {@link UserService}.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */

@RequiredArgsConstructor

@Service
// @Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

<<<<<<< HEAD
  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Autowired
  private UserRepository userRepo;
=======
  private final UserRepository userRepo;
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig

	@Override
//	@Transactional
	public void add(User user) {
<<<<<<< HEAD
<<<<<<< HEAD

    repository.save(user);
=======
	//	userDao.add(user);
=======

<<<<<<< HEAD
	  // userDao.add(user);

>>>>>>> change from dao to repository
=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
    userRepo.save(user);
>>>>>>> add repository save mithod
	}

	@Override
	public List<User> listUsers() {

<<<<<<< HEAD
<<<<<<< HEAD
    return repository.findAll();
=======
		// return userDao.listUsers();

=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
    return userRepo.findAll();
>>>>>>> change from dao to repository
	}

	@Override
	public User getUserById(Long id) {

<<<<<<< HEAD
<<<<<<< HEAD
    return repository.getOne(id);
=======
		//return userDao.getUserById(id);

=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
    return userRepo.findById(id).stream().findAny().orElse(null);
>>>>>>> change from dao to repository
	}

	@Override
//	@Transactional
	public void update(User user) {
<<<<<<< HEAD
<<<<<<< HEAD

    repository.setUserInfoById(

        user.getFirstName(), 
        user.getLastName(), 
        user.getEmail(), 
        user.getPassword(), 
        user.getId()
    );
=======
		
    //userDao.update(user);
=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig

    userRepo.save(user);
>>>>>>> change from dao to repository
	}

	@Override
	// @Transactional
	public void delete(User user) {

<<<<<<< HEAD
<<<<<<< HEAD
		repository.delete(user);
=======
		//userDao.delete(user);

=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
    userRepo.delete(user);
>>>>>>> change from dao to repository
	}

	@Override
	public User getUserByEmail(String email) {

<<<<<<< HEAD
<<<<<<< HEAD
    return repository.findByEmail(email);
=======
		//return userDao.getUserByEmail(email);
    
=======
>>>>>>> delete DataSource config, HibernateConfig, PersistenceConfig
    return userRepo.findByEmail(email);
>>>>>>> change from dao to repository
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