package ru.bogatov.AutoRent.Entities;

import lombok.Data;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name= "puncts" )
public class Punct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;
}
