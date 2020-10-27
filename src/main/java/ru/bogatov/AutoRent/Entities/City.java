package ru.bogatov.AutoRent.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Data
@Table(name = "cities")
@ToString
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "available_cars")
    private String availableCars;

    @OneToMany( fetch = FetchType.EAGER)
    private Set<Punct> puncts;

    public City(){}
}
