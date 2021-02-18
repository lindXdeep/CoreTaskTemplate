package jm.task.core.hiber.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jm.task.core.hiber.service.UserBuilder;
import lombok.Data;

/**
 * User
 */
@Data
@Entity
@Table(name="users")
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

    @JoinColumn(name = "cars_id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Car car;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(final UserBuilder userBuilder){
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.email = userBuilder.getEmail();
        this.car = userBuilder.getCar();
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", First Name='" + getFirstName() + "'" +
            ", Last Name='" + getLastName() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Car='" + car.getModel() + ":" + car.getSeries() + "'" +
            "}";
    }
}
