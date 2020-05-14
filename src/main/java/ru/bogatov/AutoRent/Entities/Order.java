package ru.bogatov.AutoRent.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer price;
    private Calendar date_from;
    private Calendar date_to;
    private String status;
    private String deliver_address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "punct_id_from")
    private Punct punct_from;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "punct_id_to")
    private Punct punct_to;
    @OneToMany( fetch = FetchType.EAGER)
    private Set<OrderServices> orderServices;

    public Order(){}
}
