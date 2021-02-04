package jm.task.core.hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(name = "name")
    @Getter @Setter private String firstName;

    @Column(name = "last_name")
    @Getter @Setter private String lastName;

    @Column(name = "email")
    @Getter @Setter private String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", First Name='" + getFirstName() + "'" +
            ", Last Name='" + getLastName() + "'" +
            ", Email='" + getEmail() + "'" +
            "}";
    }
}
