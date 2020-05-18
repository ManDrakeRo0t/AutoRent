package ru.bogatov.AutoRent.Services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.Repositories.CarsRepo;
import ru.bogatov.AutoRent.Dao.Repositories.OrdersRepo;
import ru.bogatov.AutoRent.Dao.Repositories.PunctsRepo;
import ru.bogatov.AutoRent.Dao.Repositories.UsersRepo;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.Order;
import ru.bogatov.AutoRent.Entities.Punct;
import ru.bogatov.AutoRent.Entities.User;
import ru.bogatov.AutoRent.Forms.OrderForm;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class OrderService {
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    PunctsRepo punctsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    CarsRepo carsRepo;

    public void SaveOrder(OrderForm orderForm){
        Order order = new Order();
        Car car;
        Punct punct;
        User user;
        int price;
        car = carsRepo.findByModel(orderForm.car.substring(orderForm.car.indexOf(":")+1).trim());
        punct = punctsRepo.findByAddress(orderForm.punct);
        user = usersRepo.findByUsername(orderForm.user);

        String[] date = orderForm.date_from.split("-");
        Calendar from = new GregorianCalendar();
        from.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(orderForm.time_from),0);
        Calendar to = new GregorianCalendar();
        date = orderForm.date_to.split("-");
        to.set(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(orderForm.time_to),0);
        long diff = to.getTimeInMillis() - from.getTimeInMillis();
        long sec = diff / 1000;
        long min = sec / 60;
        long hours = min / 60;
        price =  ((int)hours * car.getPrice());

        order.setDate_from(from);
        order.setDate_to(to);
        order.setPrice(price);
        order.setOrder_status("Рассматривается");
        order.setUser(user);
        order.setPunct_from(punct);
        order.setCar(car);
        ordersRepo.save(order);

    }
}
