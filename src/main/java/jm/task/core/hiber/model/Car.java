package jm.task.core.hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jm.task.core.hiber.service.CarBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * Car
 */
@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(name="model")
    @Getter @Setter private String model;

    @Column(name = "series")
    @Getter @Setter private Integer series;

    @OneToOne(optional = true, mappedBy = "car" )
    @Getter @Setter private User owner;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car(final CarBuilder carBuilder) {
        this.model = carBuilder.getModel();
        this.series = carBuilder.getSeries();
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", Model='" + getModel() + "'" +
            ", Series='" + getSeries() + "'" +
            ", User='" + owner.getFirstName() + ":" + owner.getLastName() + "'" +
            "}";
    }
}
