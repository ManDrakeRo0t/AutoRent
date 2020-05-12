package ru.bogatov.AutoRent.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "available_cars")
    private String availableCars;

    public City(){}
}
