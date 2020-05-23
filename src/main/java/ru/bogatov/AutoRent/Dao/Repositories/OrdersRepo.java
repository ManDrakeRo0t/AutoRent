package ru.bogatov.AutoRent.Dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.Order;
import ru.bogatov.AutoRent.Entities.User;

import java.util.Calendar;

@Repository
public interface OrdersRepo extends JpaRepository<Order,Long> {
//    @Query("")
//    public void mySave(String car_id, String user_id, String punct_id_from, Integer price, Calendar date_from,Calendar date_to,String order_status);

    public Iterable<Order> getAllByUser(User user);

    public void deleteById(Integer id);

    public Order getById(Integer id);
}
