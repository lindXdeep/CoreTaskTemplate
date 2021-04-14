package io.lindx.task.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepo;

	@Override
	@Transactional
	public void add(User user) {

    userRepo.save(user);
	}

	@Override
	public List<User> listUsers() {

    return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {

    return userRepo.findById(id).stream().findAny().orElse(null);
	}

	@Override
	@Transactional
	public void update(User user) {

    userRepo.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {

    userRepo.delete(user);
	}

	@Override
	public User getUserByEmail(String email) {

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