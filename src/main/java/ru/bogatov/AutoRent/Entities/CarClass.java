package ru.bogatov.AutoRent.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car_classes")
@Data
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
}
