package jm.task.core.hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne(optional = false, mappedBy = "car" )
    private User owner;

    public Car() {
    }

    public Car(Long id, String model, Integer series) {
        this.id = id;
        this.model = model;
        this.series = series;
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", Model='" + getModel() + "'" +
            ", Series='" + getSeries() + "'" +
            "}";
    }
}
