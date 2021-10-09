package io.lindx.task.service;

import java.util.List;

import io.lindx.task.model.User;

/**
 * UserService.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
public interface UserService {

  void add(User user);

  List<User> listUsers();

  User getUserById(Long id);

  User getUserByEmail(String email);

  void update(User user);

  void delete(User user);

}
