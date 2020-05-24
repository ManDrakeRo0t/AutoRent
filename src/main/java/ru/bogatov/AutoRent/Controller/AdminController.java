package ru.bogatov.AutoRent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bogatov.AutoRent.Entities.Order;
import ru.bogatov.AutoRent.Forms.CarFilter;
import ru.bogatov.AutoRent.Forms.EditOrderForm;
import ru.bogatov.AutoRent.Services.OrderServiceable;
import ru.bogatov.AutoRent.Services.UserServiceable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administration")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    OrderServiceable orderService;
    @Autowired
    UserServiceable userService;

    @GetMapping
    public String viewOrders(@RequestParam(name = "view",required = false) String view,Model model){
        Iterable<Order> orders;
        if(view != null && view.equals("notReviewed")){
            orders = orderService.getNotReviewedOrders();
        }else{
            orders = orderService.getOrders();
        }
        model.addAttribute("orders",orders);
        return "admin";
    }

    @PostMapping("/edit")
    public String editOrder(EditOrderForm editOrderForm){
        orderService.editOrder(editOrderForm);
        return "redirect:/administration";
    }

    @PostMapping
    public String viewOrder(CarFilter filter, Model model){
        if(filter == null){
            return "redirect:/administration";
        }
        Order order = orderService.getOrderById(filter.getMaxPrice());
        if(order == null){
            model.addAttribute("msg","error");
            return "admin";
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        model.addAttribute("orders",orderList);
        return "admin";
    }
}
