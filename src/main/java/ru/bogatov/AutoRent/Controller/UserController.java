package ru.bogatov.AutoRent.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogatov.AutoRent.Services.OrderService;
import ru.bogatov.AutoRent.Services.UserService;

@Controller
@RequestMapping("/personal")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping
    public String orderList(@AuthenticationPrincipal User user, Model model){
        ru.bogatov.AutoRent.Entities.User crnt = userService.getUserByMail(user.getUsername());

        model.addAttribute("user",crnt);
        model.addAttribute("orders",orderService.getOrdersForUser(crnt));

        return "personal";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id,@AuthenticationPrincipal User user,Model model){

        orderService.deleteOrder(Integer.parseInt(id));

        return "redirect:/personal";
    }

    @PostMapping("/payment/{id}")
    public String payOrder(@PathVariable String id){
        orderService.payOrder(Integer.parseInt(id));
        return "redirect:/personal";
    }



}