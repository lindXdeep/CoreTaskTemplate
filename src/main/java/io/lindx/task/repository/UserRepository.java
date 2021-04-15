package io.lindx.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.lindx.task.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);

  User findByEmail(String email);

}
