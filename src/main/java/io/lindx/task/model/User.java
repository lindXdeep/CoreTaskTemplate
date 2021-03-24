package io.lindx.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.lindx.task.service.UserBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for User
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	public User(Long id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(final UserBuilder userBuilder) {
		this.firstName = userBuilder.getFirstName();
		this.lastName = userBuilder.getLastName();
		this.email = userBuilder.getEmail();
	}

	@Override
	public String toString() {
		return "{" + " Id='" + getId() + "'" + ", First Name='" + getFirstName() + "'" + ", Last Name='" + getLastName()
				+ "'" + ", Email='" + getEmail() + "'" + "}";
	}

}
