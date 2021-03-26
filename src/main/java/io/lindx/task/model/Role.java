package io.lindx.task.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Role
 */

@Data
@AllArgsConstructor

@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private final Long id;

  @Column(name = "role_name")
  private final String role_name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<User> users;
}