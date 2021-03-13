package jm.task.core.hiber.service;

import org.springframework.stereotype.Service;

import jm.task.core.hiber.model.User;
import lombok.Data;

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
