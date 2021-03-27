package io.lindx.task.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.lindx.task.service.UserBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model for User
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "name",      length = 100, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 150, nullable = false)
	private String lastName;

	@Column(name = "email",     length = 150, nullable = false, unique = true)
	private String email;

	@Column(name = "password",  length = 255, nullable = false)
	private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_roles",      
    joinColumns        = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles;

	public User(final UserBuilder userBuilder) {
		this.firstName = userBuilder.getFirstName();
		this.lastName = userBuilder.getLastName();
    this.password = userBuilder.getPassword();
		this.email = userBuilder.getEmail();
    this.roles = userBuilder.getRoles();
	}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
