package io.lindx.task.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import io.lindx.task.model.Role;
import io.lindx.task.model.User;
import lombok.Data;

/**
 * Builder for {@link User}.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Data

@Service
public class UserBuilder {

  private String firstName;

  private String lastName;

  private Integer age;

  private String email;

  private String password;

  private Set<Role> roles;

  public UserBuilder firstName(final String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserBuilder lastName(final String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserBuilder age(final Integer age) {
    this.age = age;
    return this;
  }

  public UserBuilder email(final String email) {
    this.email = email;
    return this;
  }

  public UserBuilder password(final String password) {
    this.password = password;
    return this;
  }

  public UserBuilder roles(final Set<Role> roles) {
    this.roles = roles;
    return this;
  }

  public User build() {
    return new User(this);
  }

}
