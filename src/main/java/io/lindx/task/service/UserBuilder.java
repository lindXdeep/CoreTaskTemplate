package io.lindx.task.service;

import org.springframework.stereotype.Service;

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

	String firstName;

	String lastName;

	String email;

	public UserBuilder firstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder lastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder email(final String email) {
		this.email = email;
		return this;
	}

	public User build() {
		return new User(this);
	}

}
