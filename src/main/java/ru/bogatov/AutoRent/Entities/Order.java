package ru.bogatov.AutoRent.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;

@Entity
//@Data
@Table(name = "my_order")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer price;
    @Column(name = "date_from" , columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date_from;
    @Column(name = "date_to" , columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date_to;
    private String details;
    public boolean status;
    private boolean payment_status;
    private boolean review;
//    private Integer car_id;
//    private Integer user_id;
//    private Integer punct_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "punct_id")
    private Punct punct_from;

    public String getDate(){
        String s = "";
        s += String.format("%tF" ,date_from.getTime());
        s += "  " + String.format("%tR" ,date_from.getTime());
        s += " - " + String.format("%tF" ,date_to.getTime());
        s += "  " + String.format("%tR" ,date_to.getTime());
        return s;
    }

    public Order(){}
}
