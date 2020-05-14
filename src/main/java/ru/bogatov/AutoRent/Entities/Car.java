package ru.bogatov.AutoRent.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name = "cars")
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String mark;
    private String model;
    private Integer price;
    private String pictures;
    private Integer places;
    private String gearbox;
    private String fuel;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private CarClass carClass;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Car(){}


}
