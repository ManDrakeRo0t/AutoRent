package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bogatov.AutoRent.Dao.CarsRepo;
import ru.bogatov.AutoRent.Dao.OrdersRepo;
import ru.bogatov.AutoRent.Dao.PunctsRepo;
import ru.bogatov.AutoRent.Dao.UsersRepo;
import ru.bogatov.AutoRent.Entities.*;
import ru.bogatov.AutoRent.Forms.EditOrderForm;
import ru.bogatov.AutoRent.Forms.OrderForm;

import javax.transaction.Transactional;
import java.util.*;


public class OrderService implements OrderServiceable {
    private final String def = "Рассматривается";
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    PunctsRepo punctsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    CarsRepo carsRepo;

    public Map<String, String> getOrdersMap(){

        Map<String, String> map = new HashMap<>();
        List<Order> orders = ordersRepo.findAll();
        for(Order o : orders){
            City city = o.getPunct_from().getCity();
            List<Order> orderList = ordersRepo.getAllByCar(o.getCar());
            for(Iterator<Order> iterator = orderList.iterator();iterator.hasNext();){
                Order or = iterator.next();
                if(or.isReview()){
                    iterator.remove();
                    continue;
                }
                if(!or.getPunct_from().getCity().getName().equals(city.getName())){
                    iterator.remove();
                    continue;
                }
                if(or.getId().equals(o.getId())){
                    iterator.remove();
                }
            }
//            for(Order or : orderList){
//                if(!or.getPunct_from().getCity().getName().equals(city.getName())){
//                    orderList.remove(or);
//                    continue;
//                }
//                if(or.getId().equals(o.getId())){
//                    orderList.remove(or);
//                }
//            }
            //orderList.removeIf(or -> !or.getPunct_from().getCity().equals(city));
            List<String> stringList = new ArrayList<>();
            orderList.forEach(x -> stringList.add("Номер "+ x.getId() + " | " + x.getDate() + "   :   Стоимость " + x.getPrice().toString() +" р. "));
            StringBuilder res = new StringBuilder();
            for(String s : stringList){
                res.append(s);
            }
            map.put(o.getId().toString()+"o",res.toString());
        }
        return map;
    }

    public void editOrder(EditOrderForm form){
        Order order = this.getOrderById(form.id);

        if(order.isReview()) return;

        if(form.status!=null){
            if(form.details.equals(def)) order.setDetails("Рассмотрен");
            order.setStatus(true);
        }else{
            if(form.details.equals(def)) return;
            order.setDetails(form.details);
        }

        order.setReview(true);
        ordersRepo.save(order);
    }

    public Iterable<Order> getOrders(){return ordersRepo.findAll();}

    public Order getOrderById(Integer id){return ordersRepo.getById(id);}

    public Iterable<Order> getNotReviewedOrders(){return ordersRepo.findAllByReviewIsFalse();}

    public Iterable<Order> getOrdersForUser(User user){
        return ordersRepo.getAllByUser(user);
    }

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
        order.setDetails("Рассматривается");
        order.setUser(user);
        order.setStatus(false);
        order.setPayment_status(false);
        order.setReview(false);
        order.setPunct_from(punct);
        order.setCar(car);
        ordersRepo.save(order);

    }
    @Transactional
    public void deleteOrder(Integer id){
        ordersRepo.deleteById(id);
    }

    @Transactional
    public void payOrder(Integer id){
        Order order = ordersRepo.getById(id);

        if(order.status)order.setPayment_status(true);

        if(order.isPayment_status()){
            String address = order.getPunct_from().getCity().getName() + " " + order.getPunct_from().getAddress();
            String date = String.format("%tF" ,order.getDate_from().getTime());
            String time = String.format("%tR" ,order.getDate_from().getTime());

            order.setDetails("Вы можете забрать автомобиль по адресу : "
                            + address +
                 "\nПосле : " + date + " : " + time
                    );
        }

        ordersRepo.save(order);
    }
}
