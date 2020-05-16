package ru.bogatov.AutoRent.Entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car_classes")
//@Data
@ToString(exclude = "cars")
public class CarClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name="required_age")
    private Integer required_age;

    @OneToMany( fetch = FetchType.EAGER)
    private Set<Car> cars;

    public CarClass(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequired_age() {
        return required_age;
    }

    public void setRequired_age(Integer required_age) {
        this.required_age = required_age;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

}
