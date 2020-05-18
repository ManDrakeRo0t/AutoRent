package ru.bogatov.AutoRent.Dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.Order;

import java.util.Calendar;

@Repository
public interface OrdersRepo extends JpaRepository<Order,Long> {
//    @Query("")
//    public void mySave(String car_id, String user_id, String punct_id_from, Integer price, Calendar date_from,Calendar date_to,String order_status);
}
