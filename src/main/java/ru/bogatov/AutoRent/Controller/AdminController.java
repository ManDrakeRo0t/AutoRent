package ru.bogatov.AutoRent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.City;
import ru.bogatov.AutoRent.Entities.Order;
import ru.bogatov.AutoRent.Forms.CarFilter;
import ru.bogatov.AutoRent.Forms.CarForm;
import ru.bogatov.AutoRent.Forms.EditOrderForm;
import ru.bogatov.AutoRent.Services.CarServiceable;
import ru.bogatov.AutoRent.Services.OrderServiceable;
import ru.bogatov.AutoRent.Services.PunctsServiceable;
import ru.bogatov.AutoRent.Services.UserServiceable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/administration")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    OrderServiceable orderService;
    @Autowired
    UserServiceable userService;
    @Autowired
    CarServiceable carService;
    @Autowired
    PunctsServiceable punctsService;


    @GetMapping
    public String viewOrders(@RequestParam(name = "view",required = false) String view,Model model){
        Iterable<Order> orders;
        Iterable<Car> cars = carService.getAllCars();
        Iterable<City> cities = punctsService.getAllCities();

        if(view != null && view.equals("notReviewed")){
            orders = orderService.getNotReviewedOrders();
        }else{
            orders = orderService.getOrders();
        }
        model.addAttribute("orders",orders);
        model.addAttribute("classes",carService.getCarClasses());
        model.addAttribute("cars",cars);
        model.addAttribute("cities",punctsService.getAllCities());
        model.addAttribute("mapCars",punctsService.getCitiesForCarsMap());
        model.addAttribute("mapOrders",orderService.getOrdersMap());

        return "admin";
    }
    @PostMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable String id){
        carService.deleteCar(id);
        return "redirect:/administration";
    }

    @PostMapping("/addCar")
    public String addCar(@RequestParam("file") MultipartFile[] file, CarForm carForm,Model model) throws IOException {
        carService.addCar(file,carForm);
        return "redirect:/administration";
    }

    @PostMapping("/editOrder")
    public String editOrder(EditOrderForm editOrderForm){
        orderService.editOrder(editOrderForm);
        return "redirect:/administration";
    }
    @PostMapping("/editCar")
    public String editCar(@RequestParam Map<String,String> form){
        carService.saveCar(form);
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
