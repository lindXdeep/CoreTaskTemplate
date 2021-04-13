package io.lindx.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lindx.task.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Modifying
  @Query("update User u set u.name = ?1, u.last_name = ?2, u.email = ?3, u.password = ?4, where u.id = ?5")
  void setUserInfoById(String firstName, String lastName, String email, String password, Long userId);

  @Query("select u from User u where u.email = ?1")
  User findByEmail(String email);
}