package ru.bogatov.AutoRent.Services;

import ru.bogatov.AutoRent.Entities.Order;
import ru.bogatov.AutoRent.Entities.User;
import ru.bogatov.AutoRent.Forms.EditOrderForm;
import ru.bogatov.AutoRent.Forms.OrderForm;

import java.util.List;
import java.util.Map;

public interface OrderServiceable {
    void editOrder(EditOrderForm form);
    Iterable<Order> getOrders();
    Order getOrderById(Integer id);
    Iterable<Order> getNotReviewedOrders();
    Iterable<Order> getOrdersForUser(User user);
    void SaveOrder(OrderForm orderForm);
    public void deleteOrder(Integer id);
    public void payOrder(Integer id);
    public Map<String,String> getOrdersMap();
}
