package ru.bogatov.AutoRent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bogatov.AutoRent.Entities.User;
import ru.bogatov.AutoRent.Forms.OrderForm;
import ru.bogatov.AutoRent.Services.*;

@Controller
public class MainController {
    @Autowired
    PunctsServiceable punctsService;
    @Autowired
    CarServiceable carService;
    @Autowired
    OrderServiceable orderService;

    @GetMapping("")
    public String home(){
        return "home";
    }

    @GetMapping("/locations")
    public String getLocations(Model model){
        model.addAttribute("cities",punctsService.getAllCities());
        return "locations";
    }

    @GetMapping("/order")
    public String newOrder(@RequestParam(name = "city",required = false,defaultValue = "def")String city, Model model){
        model.addAttribute("cities",punctsService.getAllCities());
        if(!city.equals("def")){
            model.addAttribute("puncts",punctsService.getPunctsForCity(city));
            model.addAttribute("cars",punctsService.getCarsForCity(city));
            model.addAttribute("crnCity",city);
        }
        return "order";
    }

    @PostMapping("/order")
    public String confirmOrder(OrderForm orderForm,Model model){
        orderService.SaveOrder(orderForm);
        model.addAttribute("msg","Ваш заказ принят,проверяйте статут заказа в ");
        return "order";
    }

}
