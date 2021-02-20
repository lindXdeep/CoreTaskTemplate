package jm.task.core.hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jm.task.core.hiber.service.CarBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Car
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="model")
    private String model;

    @Column(name = "series")
    private Integer series;

    @OneToOne(optional = true, mappedBy = "car" )
    private User owner;

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
