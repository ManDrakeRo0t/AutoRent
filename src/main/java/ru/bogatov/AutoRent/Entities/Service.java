package ru.bogatov.AutoRent.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    @OneToMany( fetch = FetchType.EAGER)
    private Set<OrderServices> orderServices;

    public Service(){}

}
